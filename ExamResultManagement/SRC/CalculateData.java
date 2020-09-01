public class CalculateData {

    public static int getTotalMarks(int c, int m, int s, int e, int h) {
        return (c + m + s + e + h);
    }

    public static float getPercentage(int marks) {
        float per = (float) marks / 5;
        return Float.parseFloat(String.format("%.2f", per));
    }

    public static String getDivision(float percent) {

        if (percent >= 60.0f) {
            return "First";
        } else if (percent < 60.0f && percent >= 45.0f) {
            return "Second";
        } else if (percent < 45.0f && percent >= 33.0f) {
            return "Third";
        } else {
            return "Fail";
        }
    }
}
