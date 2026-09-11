public class CustomerIdentityVerification {
    public static String reverseCustomerName(String customerName) {
        if (customerName == null) {
            return null;
        }
        char[] chars = customerName.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
    public static void main(String[] args) {
      String[] sampleNames = {"Sunil", "Alice", "Karthik", "Noor"};
        for (String customerName : sampleNames) {
            String reversed = reverseCustomerName(customerName);
            System.out.println("Original Name: " + customerName);
            System.out.println("Reversed Name: " + reversed);
            System.out.println("--------------------------------");
        }
    }
}
