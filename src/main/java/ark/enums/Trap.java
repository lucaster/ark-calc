package ark.enums;

import java.util.Set;

public enum Trap {

	AcidSlime(16, 1.6, null, null, 60, null);

	final private double multiplier;
	final private int damage;
	final private int points;
	final private TrapType kind;
	final private TrapAlign align;

	Trap(final int damage, 
		 final double multiplier, 
		 final TrapType kind,
		 final TrapAlign align, 
		 final int points,
		 final Set<TrapEffect> effects) {
		
		this.damage = damage;
		this.multiplier = multiplier;
		this.points = points;
		this.kind = kind;
		this.align = align;
	}
}