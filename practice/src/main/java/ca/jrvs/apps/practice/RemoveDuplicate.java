package ca.jrvs.apps.practice;

public class RemoveDuplicate {
    static class node {
        int data;
        node next;
    }

    public static node removeDuplicate(node head) {
        if (head == null) {
            return null;
        }
        node curr = head;
        while (curr.next != null) {
            if (curr.data == curr.next.data) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicate rd = new RemoveDuplicate();
        node head = new node();
        head.data = 1;
        node n1 = new node();
        n1.data = 1;
        node n2 = new node();
        n2.data = 2;
        node n3 = new node();
        n3.data = 3;
        node n4 = new node();
        n4.data = 4;
        node n5 = new node();
        n5.data = 5;
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;
        node newHead = removeDuplicate(head);
        while (newHead != null) {
            System.out.println(newHead.data);
            newHead = newHead.next;
        }
    }
}
