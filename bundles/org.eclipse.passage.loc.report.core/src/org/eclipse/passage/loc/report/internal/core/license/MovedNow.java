package org.eclipse.passage.loc.report.internal.core.license;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @since 0.2
 */
public final class MovedNow implements Supplier<Date> {

	private final Function<LocalDate, LocalDate> move;

	public MovedNow(Function<LocalDate, LocalDate> move) {
		this.move = move;
	}

	@Override
	public Date get() {
		return Date.from(//
				move.apply(LocalDate.now())//
						.atStartOfDay()//
						.atZone(ZoneId.systemDefault())//
						.toInstant());
	}

}
