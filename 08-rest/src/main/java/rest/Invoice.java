package rest;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Invoice {
	private int id;
	private float amountInEur;

	public Invoice() {

	}

	public Invoice(final int id, final float amountInEur) {
		this.id = id;
		this.amountInEur = amountInEur;
	}

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(final int newId) {
		this.id = newId;
	}

	@XmlElement(name = "amountInEur")
	public float getAmountInEur() {
		return amountInEur;
	}

	public void setAmountInEur(final float newAmountInEur) {
		this.amountInEur = newAmountInEur;
	}
}
