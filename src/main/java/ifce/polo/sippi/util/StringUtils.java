package ifce.polo.sippi.util;

public class StringUtils
{

/* --------------------------------------------------------------------------------------------- */

    public static final StringBuilder capitalCase(String str, StringBuilder sb) {
        return sb.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
    }

/* --------------------------------------------------------------------------------------------- */

    public static String underlineCase(String str) {
        return str.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }

/* --------------------------------------------------------------------------------------------- */

    public static String concat(Object... tokens) {
        return concat(new StringBuilder(), tokens).toString();
    }

/* --------------------------------------------------------------------------------------------- */

    public static StringBuilder concat(StringBuilder sb, Object... tokens)
    {
        for (Object token : tokens) {
            sb.append(token);
        }

        return sb;
    }

/* --------------------------------------------------------------------------------------------- */

    public static String getInitials(String name)
    {
        if (name == null) {
            return null;
        }

        String[] tokens = name.trim().replaceAll("\\s+", " ").split(" ");
        StringBuilder sb = new StringBuilder();

        for (String token : tokens) {
            sb.append(Character.toUpperCase(token.charAt(0)));
        }

        return sb.toString();
    }

/* --------------------------------------------------------------------------------------------- */

}
