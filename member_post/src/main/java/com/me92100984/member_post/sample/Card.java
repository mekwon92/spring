package com.me92100984.member_post.sample;
import java.util.Arrays;

//enum
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
//	static final int CLOVER = 0;
//	static final int HEART = 1;
//	static final int DIAMOND = 2;
//	static final int SPADE = 3;
	
	Kind kind;	
	
	public static void main(String[] args) {
//		Card card = new Card(CLOVER);
		Card card = new Card(Kind.CLOVER);
		System.out.println(card);
		System.out.println(card.kind.ordinal());
		System.out.println(Kind.DIAMOND.ordinal());
//		card.kind = HEART;
//		card.kind = 10;
		Kind[] kinds = Kind.values();
		System.out.println(Arrays.toString(kinds));
		
		for(Kind k : kinds) {
			System.out.println(k.getName());
		}
	}
}
