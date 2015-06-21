import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by driabchenko on 19/06/15.
 */
public class NumericIP {
    public static void main(String[] args) {
        String[] str = {
                "192.168.1.0",
                "192.168.124.45",
                "127.0.0.1",
        };
        for (String s : str) {
            System.out.println(s + " - " + ipEncode(s) + ", " + ipDecode(ipEncode(s)));
        }
    }

    private static Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
    public static long ipEncode(String ip) {
        long result = -1;
        if (ip != null) {
            Matcher matcher = pattern.matcher(ip);
            if (matcher.find()) {
                result = 0;
                for (int i = 1; i <= 4; i ++) {
                    long octet = Long.parseLong(matcher.group(i));
                    result |= octet << (4 - i) * 0x08;
                }
            }

        }
        return result;
    }

    public static String ipDecode(long ip) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i ++) {
            long octet = (ip & 0xFF << (3 - i) * 0x08) >> 0x08 * (3 - i);
            result.append(octet);
            if (i < 3) {
                result.append('.');
            }
        }
        return result.toString();
    }
}
