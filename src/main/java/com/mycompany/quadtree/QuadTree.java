/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quadtree;

/**
 *
 * @author muham
 */
public class QuadTree {
    Node parent;
    int[][] veri;
    
    public QuadTree(int [][] d){
        this.veri=d;
        this.add(0, 250, 0, 250, null); //Converts 2d array to quadtree
        
    }
     final void add(int startHeight,int height,int startWidth,int width,Node node){
        int midofH=(height+startHeight)/2;
        int midofW=(width+startWidth)/2;
        if (node==null) {node=new Node();parent=node;}
        node.childs[0]=new Node();
        node.childs[1]=new Node();
        node.childs[2]=new Node();
        node.childs[3]=new Node();
        int temp=veri[startHeight][startWidth];//first element of every piece
        loop1:
        for (int i = startHeight; i < midofH; i++) {//top left (1)
            for (int j = startWidth; j < midofW; j++) {
                if (temp!=veri[i][j]){
                    add(startHeight, midofH, startWidth, midofW, node.childs[0]);
                    break loop1; }//eğer seçilen parçanın elemanlarının tümü aynı değilse
                                //fonsiyonu o parça için birdaha çağırır ve bitirir.
            }
            if (i==midofH-1) {
                node.childs[0].data=temp;
                
            }//If all of the checked square piece is the same color, that branch gets that color
        }
        temp=veri[startHeight][midofW];//first element of every piece
        loop2:
        for (int i = startHeight; i < midofH; i++) {//right top(2)
            for (int j = midofW; j < width; j++) {
                if (temp!=veri[i][j]) {
                    add(startHeight, midofH, midofW, width, node.childs[1]);
                    break loop2; }   
            }
            if (i==midofH-1) {
                node.childs[1].data=temp;}
        }
        temp=veri[midofH][startWidth];//first element of every piece
        loop3:
        for (int i = midofH; i < height; i++) {//levfBottom (3)
            for (int j = startWidth; j < midofW; j++) {
                if (temp!=veri[i][j]) {
                    add(midofH, height, startWidth, midofW, node.childs[2]);
                    break loop3; }  
            }
            if (i==height-1) {
                node.childs[2].data=temp;}
        }
        temp=veri[midofH][midofW];//first element of every piece
        loop4:
        for (int i = midofH; i < height; i++) {//rightbottom (4)
            for (int j = midofW; j < width; j++) {
                if (temp!=veri[i][j]) {
                    add(midofH, height, midofW, width, node.childs[3]);
                    break loop4; } 
            }
            if (i==height-1) {
                node.childs[3].data=temp;}
        }
        //THESE 4 LOOP CAN BE MIGRATE INTO A LOOP
        
    }
    
     
   
    int[][] veriler;
    public int [][] read(Node Parent,int startofHeight,int endOfHeight,int startOfWidth,int endOfWidth){
        if (Parent.equals(parent)) {
            veriler=new int[veri.length][veri.length];
        }
        if (Parent.childs[0]==null) {//if an child is null so this child's chids also doesnt exsist
            for (int i = startofHeight; i < endOfHeight; i++) {
                for (int j = startOfWidth; j < endOfWidth; j++) {
                    veriler[i][j]=Parent.data;
                }
            }
        }else {
            read(Parent.childs[0],  startofHeight, (endOfHeight+startofHeight)/2, startOfWidth, (startOfWidth+endOfWidth)/2);
            read(Parent.childs[1],  startofHeight, (endOfHeight+startofHeight)/2, (startOfWidth+endOfWidth)/2, endOfWidth);
            read(Parent.childs[2],  (endOfHeight+startofHeight)/2, endOfHeight, startOfWidth, (startOfWidth+endOfWidth)/2);
            read(Parent.childs[3],  (endOfHeight+startofHeight)/2, endOfHeight, (startOfWidth+endOfWidth)/2, endOfWidth);
        }
        
        return veriler;
    }
  }  
class Node{
    int data;
    Node[] childs;
    public Node(){
        data=-1;
        childs=new Node[4];
        
    }
   
}
