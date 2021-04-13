package products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Data {
	private int id;
	private String name;
	private String description;
	private String image;
	private String price;
	private String discount_amount;
	private boolean status;
	private Categories categories;
	private String message;
}
