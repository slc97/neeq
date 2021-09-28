package cn.com.neeq.ubs.shell.builtin;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.MessageInterpolator.Context;
import javax.validation.metadata.ConstraintDescriptor;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.CommandRegistry;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.ParameterDescription;
import org.springframework.shell.ParameterResolver;
import org.springframework.shell.Utils;
import org.springframework.shell.standard.CommandValueProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author 宋立成
 * @date 2021/8/17 10:29
 */
@ShellComponent
public class Help extends BaseBuiltinCommand {
    private final List<ParameterResolver> parameterResolvers;
    private CommandRegistry commandRegistry;
    private MessageInterpolator messageInterpolator = Validation.buildDefaultValidatorFactory().getMessageInterpolator();

    @Autowired
    public Help(List<ParameterResolver> parameterResolvers) {
        this.parameterResolvers = parameterResolvers;
    }

    @Autowired
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Autowired(
            required = false
    )
    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.messageInterpolator = validatorFactory.getMessageInterpolator();
    }

    @ShellMethod(
            value = "Display help about available commands.",
            prefix = "-"
    )
    public CharSequence help(@ShellOption(defaultValue = "__NULL__",valueProvider = CommandValueProvider.class,value = {"-C", "--command"},help = "The command to obtain help for.") String command) throws IOException {
        return command == null ? this.listCommands() : this.documentCommand(command);
    }

    private CharSequence documentCommand(String command) {
        MethodTarget methodTarget = (MethodTarget)this.commandRegistry.listCommands().get(command);
        if (methodTarget == null) {
            throw new IllegalArgumentException("Unknown command '" + command + "'");
        } else {
            AttributedStringBuilder result = (new AttributedStringBuilder()).append("\n\n");
            List<ParameterDescription> parameterDescriptions = this.getParameterDescriptions(methodTarget);
            this.documentCommandName(result, command, methodTarget.getHelp());
            this.documentSynopsys(result, command, parameterDescriptions);
            this.documentOptions(result, parameterDescriptions);
            this.documentAliases(result, command, methodTarget);
            this.documentAvailability(result, methodTarget);
            result.append("\n");
            return result;
        }
    }

    private void documentCommandName(AttributedStringBuilder result, String command, String help) {
        result.append("NAME", AttributedStyle.BOLD).append("\n\t");
        result.append(command).append(" - ").append(help).append("\n\n");
    }

    private void documentSynopsys(AttributedStringBuilder result, String command, List<ParameterDescription> parameterDescriptions) {
        result.append("SYNOPSYS", AttributedStyle.BOLD).append("\n\t");
        result.append(command, AttributedStyle.BOLD);
        result.append(" ");

        for(Iterator var4 = parameterDescriptions.iterator(); var4.hasNext(); result.append("  ")) {
            ParameterDescription description = (ParameterDescription)var4.next();
            if (description.defaultValue().isPresent() && description.formal().length() > 0) {
                result.append("[");
            }

            List<String> keys = description.keys();
            if (!keys.isEmpty()) {
                if (!description.mandatoryKey()) {
                    result.append("[");
                }

                result.append(this.first(keys), AttributedStyle.BOLD);
                if (!description.mandatoryKey()) {
                    result.append("]");
                }

                if (!description.formal().isEmpty()) {
                    result.append(" ");
                }
            }

            if (description.defaultValueWhenFlag().isPresent()) {
                result.append("[");
            }

            this.appendUnderlinedFormal(result, description);
            if (description.defaultValueWhenFlag().isPresent()) {
                result.append("]");
            }

            if (description.defaultValue().isPresent() && description.formal().length() > 0) {
                result.append("]");
            }
        }

        result.append("\n\n");
    }

    private void documentOptions(AttributedStringBuilder result, List<ParameterDescription> parameterDescriptions) {
        if (!parameterDescriptions.isEmpty()) {
            result.append("OPTIONS", AttributedStyle.BOLD).append("\n");
        }

        for(Iterator var3 = parameterDescriptions.iterator(); var3.hasNext(); result.append('\n')) {
            ParameterDescription description = (ParameterDescription)var3.next();
            result.append("\t").append((CharSequence)description.keys().stream().collect(Collectors.joining(" or ")), AttributedStyle.BOLD);
            if (description.formal().length() > 0) {
                if (!description.keys().isEmpty()) {
                    result.append("  ");
                }

                description.defaultValueWhenFlag().ifPresent((f) -> {
                    result.append('[');
                });
                this.appendUnderlinedFormal(result, description);
                description.defaultValueWhenFlag().ifPresent((f) -> {
                    result.append(']');
                });
                result.append("\n\t");
            } else if (description.keys().size() > 1) {
                result.append("\n\t");
            }

            result.append("\t");
            result.append(description.help()).append('\n');
            if (description.defaultValue().isPresent()) {
                result.append("\t\t[Optional, default = ", AttributedStyle.BOLD).append((CharSequence)description.defaultValue().get(), AttributedStyle.BOLD.italic());
                description.defaultValueWhenFlag().ifPresent((s) -> {
                    result.append(", or ", AttributedStyle.BOLD).append(s, AttributedStyle.BOLD.italic()).append(" if used as a flag", AttributedStyle.BOLD);
                });
                result.append("]", AttributedStyle.BOLD);
            } else if (description.defaultValueWhenFlag().isPresent()) {
                result.append("\t\t[Mandatory, default = ", AttributedStyle.BOLD).append((CharSequence)description.defaultValueWhenFlag().get(), AttributedStyle.BOLD.italic()).append(" when used as a flag]", AttributedStyle.BOLD);
            } else {
                result.append("\t\t[Mandatory]", AttributedStyle.BOLD);
            }

            result.append('\n');
            if (description.elementDescriptor() != null) {
                Iterator var5 = description.elementDescriptor().getConstraintDescriptors().iterator();

                while(var5.hasNext()) {
                    ConstraintDescriptor<?> constraintDescriptor = (ConstraintDescriptor)var5.next();
                    String friendlyConstraint = this.messageInterpolator.interpolate(constraintDescriptor.getMessageTemplate(), new Help.DummyContext(constraintDescriptor));
                    result.append("\t\t[" + friendlyConstraint + "]\n", AttributedStyle.BOLD);
                }
            }
        }

    }

    private void documentAliases(AttributedStringBuilder result, String command, MethodTarget methodTarget) {
        Set<String> aliases = (Set)this.commandRegistry.listCommands().entrySet().stream().filter((e) -> {
            return ((MethodTarget)e.getValue()).equals(methodTarget);
        }).map(Entry::getKey).filter((c) -> {
            return !command.equals(c);
        }).collect(Collectors.toCollection(TreeSet::new));
        if (!aliases.isEmpty()) {
            result.append("ALSO KNOWN AS", AttributedStyle.BOLD).append("\n");
            Iterator var5 = aliases.iterator();

            while(var5.hasNext()) {
                String alias = (String)var5.next();
                result.append('\t').append(alias).append('\n');
            }
        }

    }

    private void documentAvailability(AttributedStringBuilder result, MethodTarget methodTarget) {
        Availability availability = methodTarget.getAvailability();
        if (!availability.isAvailable()) {
            result.append("CURRENTLY UNAVAILABLE", AttributedStyle.BOLD).append("\n");
            result.append('\t').append("This command is currently not available because ").append(availability.getReason()).append(".\n");
        }

    }

    private String first(List<String> keys) {
        return (String)keys.iterator().next();
    }

    private CharSequence listCommands() {
        Map<String, MethodTarget> commandsByName = this.commandRegistry.listCommands();
        SortedMap<String, Map<String, MethodTarget>> commandsByGroupAndName = (SortedMap)commandsByName.entrySet().stream().collect(Collectors.groupingBy((e) -> {
            return ((MethodTarget)e.getValue()).getGroup();
        }, TreeMap::new, Collectors.toMap(Entry::getKey, Entry::getValue)));
        AttributedStringBuilder result = new AttributedStringBuilder();
        result.append("AVAILABLE COMMANDS\n\n", AttributedStyle.BOLD);
        commandsByGroupAndName.forEach((group, commandsInGroup) -> {
            result.append("".equals(group) ? "Default" : group, AttributedStyle.BOLD).append('\n');
            Map<MethodTarget, SortedSet<String>> commandNamesByMethod = (Map)commandsInGroup.entrySet().stream().collect(Collectors.groupingBy(Entry::getValue, Collectors.mapping(Entry::getKey, Collectors.toCollection(TreeSet::new))));
            commandNamesByMethod.entrySet().stream().sorted(this.sortByFirstCommandName()).forEach((e) -> {
                result.append(this.isAvailable((MethodTarget)e.getKey()) ? "        " : "      * ").append(String.join(", ", (Iterable)e.getValue()), AttributedStyle.BOLD).append(": ").append(((MethodTarget)e.getKey()).getHelp()).append('\n');
            });
            result.append('\n');
        });
        if (commandsByName.values().stream().distinct().anyMatch((m) -> {
            return !this.isAvailable(m);
        })) {
            result.append("Commands marked with (*) are currently unavailable.\nType `help <command>` to learn more.\n\n");
        }

        return result;
    }

    private Comparator<Entry<MethodTarget, SortedSet<String>>> sortByFirstCommandName() {
        return Comparator.comparing((e) -> {
            return (String)((SortedSet)e.getValue()).first();
        });
    }

    private boolean isAvailable(MethodTarget methodTarget) {
        return methodTarget.getAvailability().isAvailable();
    }

    private void appendUnderlinedFormal(AttributedStringBuilder result, ParameterDescription description) {
        char[] var3 = description.formal().toCharArray();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            char c = var3[var5];
            if (c != ' ') {
                result.append("" + c, AttributedStyle.DEFAULT.underline());
            } else {
                result.append(c);
            }
        }

    }

    private List<ParameterDescription> getParameterDescriptions(MethodTarget methodTarget) {
        return (List)Utils.createMethodParameters(methodTarget.getMethod()).flatMap((mp) -> {
            return this.parameterResolvers.stream().filter((pr) -> {
                return pr.supports(mp);
            }).limit(1L).flatMap((pr) -> {
                return pr.describe(mp);
            });
        }).collect(Collectors.toList());
    }

    private static class DummyContext implements Context {
        private final ConstraintDescriptor<?> descriptor;

        private DummyContext(ConstraintDescriptor<?> descriptor) {
            this.descriptor = descriptor;
        }

        public ConstraintDescriptor<?> getConstraintDescriptor() {
            return this.descriptor;
        }

        public Object getValidatedValue() {
            return null;
        }

        public <T> T unwrap(Class<T> type) {
            return null;
        }
    }

    public interface Command {
    }
}

