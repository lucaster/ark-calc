package ark.enums;

import static ark.enums.TrapAlign.Humiliating;
import static ark.enums.TrapEffect.Bind;
import static ark.enums.TrapEffect.ForcedArmorBreak;
import static ark.enums.TrapType.Ceiling;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum Trap {

	AcidSlime(16, 1.6, Ceiling, Humiliating, 60, setOf(Bind, ForcedArmorBreak));

	final private double multiplier;
	final private int damage;
	final private int points;
	final private TrapType kind;
	final private TrapAlign align;
	final private Set<TrapEffect> effects;

	@SafeVarargs
	final private static <T> Set<T> setOf(final T... things) {
		return new HashSet<T>(Arrays.asList(things));
	}

	public double getMultiplier() {
		return multiplier;
	}

	public int getDamage() {
		return damage;
	}

	public int getPoints() {
		return points;
	}

	public TrapType getKind() {
		return kind;
	}

	public TrapAlign getAlign() {
		return align;
	}

	public Set<TrapEffect> getEffects() {
		return new HashSet<TrapEffect>(effects);
	}

	Trap(final int damage, final double multiplier, final TrapType kind,
			final TrapAlign align, final int points) {
		this(damage, multiplier, kind, align, points, Collections.emptySet());
	}

	Trap(final int damage, final double multiplier, final TrapType kind,
			final TrapAlign align, final int points,
			final Set<TrapEffect> effects) {
		this.damage = damage;
		this.multiplier = multiplier;
		this.points = points;
		this.kind = kind;
		this.align = align;
		this.effects = new HashSet<TrapEffect>(effects);
	}
}