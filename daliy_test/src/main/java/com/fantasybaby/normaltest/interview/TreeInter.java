package com.fantasybaby.normaltest.interview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**     7
 *   6     5
 *  8 9   3  2
 *
 * 7 6 5 8 9 3 2
 * @author reid.liu
 * @date 2018-08-27 15:16
 */
public class TreeInter {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class  Node{
        private String value;
        private Node leftNode;
        private Node rightNode;
    }
    public Node initData(){
        Node n4 = new Node("8",null,null);
        Node n5 = new Node("9",null,null);
        Node n6 = new Node("3",null,null);
        Node n7 = new Node("2",null,null);
        Node n2 = new Node("6",n4,n5);
        Node n3 = new Node("5",n6,n7);
        return  new Node("7",n2,n3);
    }
    public void printEachNode(Node node){
        if(Objects.isNull(node.leftNode)){
            return;
        }
        Node leftNode = node.leftNode;
        Node rightNode = node.rightNode;
        System.out.println(leftNode.value+"--");
        System.out.println(rightNode.value+"--");
        printEachNode(leftNode);
        printEachNode(rightNode);
    }

    public static void main(String[] args) {
        TreeInter treeInter = new TreeInter();
        Node node = treeInter.initData();
        System.out.println(node.value);
        treeInter.printEachNode(node);
    }
}
