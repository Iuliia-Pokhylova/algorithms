import lang.ListNode;

/*
    https://leetcode.com/submissions/detail/30820454/
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);

        ListNode l2 = new ListNode(5);

        System.out.println(addTwoNumbers2(l1, l2).asString());
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int addToNext = 0;
        ListNode result = new ListNode(0);
        ListNode tail = result;
        while (l1 != null || l2 != null || addToNext != 0) {
            int sum = addToNext;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            addToNext = sum / 10;
            sum %= 10;
            tail.next = new ListNode(sum);
            tail = tail.next;
        }
        return result.next;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int addToNext = 0;
        ListNode result = l1;
        while (l1 != null || l2 != null) {
            l1.val += addToNext;
            if (l2 != null) {
                l1.val += l2.val;
                l2 = l2.next;
            }
            addToNext = l1.val / 10;
            l1.val %= 10;
            if (l1.next == null && (l2 != null || addToNext != 0)) {
                if (l2 == null) {
                    l1.next = new ListNode(addToNext);
                    l1 = l1.next;
                } else {
                    l1.next = new ListNode(0);
                }
            }
            l1 = l1.next;
        }
        return result;
    }
}
