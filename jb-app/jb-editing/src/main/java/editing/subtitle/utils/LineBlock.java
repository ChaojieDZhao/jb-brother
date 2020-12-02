package editing.subtitle.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LineBlock
{
	private int num;

	private String timeCode;

	private String egLine;

	private String chLine;
}