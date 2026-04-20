package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Barrowed {
	public UUID BarrowedId;
	public String BookId;
	public UUID VisitorId;
	public LocalDateTime BarrStartTime;
	public LocalDateTime BarrEndTime;
}
