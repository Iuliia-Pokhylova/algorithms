import lang.ListNode;

/*
    https://leetcode.com/submissions/detail/30827019/
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode list = ListNode.fromArray(new int[] { 1, 2, 3, 4 });
        System.out.println(list.asString());
        System.out.println(swapPairs(list).asString());
    }

    public static ListNode swapPairs(ListNode head) {
        if (head != null) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next = head.next;

            while (curr != null && next != null) {
                if (prev == null) {
                    prev = head.next;
                    head = prev;
                } else {
                    prev.next = next;
                }
                curr.next = next.next;
                next.next = curr;

                prev = curr;
                curr = curr.next;
                if (curr != null) {
                    next = curr.next;
                }
            }
        }
        return head;
    }
}
