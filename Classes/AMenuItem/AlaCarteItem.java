package Classes.AMenuItem;

/**
 * The AlaCarteItem Class 
 * extends from AMenuItem
 * @author Lingyi
 * @version 1.0
 * @since 2021-11-01
 */
public class AlaCarteItem extends AMenuItem  {
    
    /**
     * Constructor to pass in all required parameters.
     *
     * @param id          AlaCarte item's ID.
     * @param name        AlaCarte item's name.
     * @param description AlaCarte item's description.
     * @param price       AlaCarte item's price.
     */
	public AlaCarteItem(int id, String name, double price, String description)
	{
        super(id, name, price, description);
        this.itemType = TYPE.ALACARTE;
    }
}
    


