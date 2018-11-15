package leetCode;

/**
 * Created by liyong on 16/7/10.
 */
public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    if (null == head || null == head.next) {
      return head;
    }
    ListNode last = null;

    while (null != head.next) {
      ListNode next = head.next;
      head.next = last;
      last = head;
      head = next;
    }
    head.next = last;
    return head;
  }



 public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

}
