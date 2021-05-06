package com.example.mall.portal.controller;


import com.fasterxml.jackson.databind.util.LinkedNode;

class Solution {
    public static void main(String[] args) {
        ListNode node = new ListNode();
        node.val = 2;
        node.next = new ListNode();
        node.next.val = 4;
        node.next.next = new ListNode();
        node.next.next.val = 3;

        Solution solution = new Solution();
        solution.addTwoNumbers(node, node);
    }

    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        System.out.println(l1.val);
        System.out.println(l2.val);
        ListNode node = new ListNode();

        while(l1.next!=null){
            int val = l1.val;
            l1=l1.next;
        }

        node.val=l1.val+l2.val;
        node.next=new ListNode();

        node.val=l1.val+l2.val;
        node.next.next=new ListNode();
        node.val=l1.val+l2.val;


        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


}