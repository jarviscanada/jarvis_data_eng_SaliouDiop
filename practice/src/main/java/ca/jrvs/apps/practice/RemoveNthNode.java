package ca.jrvs.apps.practice;

public class RemoveNthNode {

    static class node {
        int data;
        node next;
    }

    public static node removeNthNodeFromEnd(node head, int n) {
        node slow = head;
        node fast = head;
        int count = 0;
        while (count < n) {
            fast = fast.next;
            count++;
        }
        if (fast == null) {
            head = head.next;
            return head;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
