package ch.amaba.shared;

import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

import com.gwtplatform.dispatch.shared.Result;

public class ChangerStatutMessageResult implements Result {

	private static final long serialVersionUID = 1L;

	private TypeMessageStatutEnum typeMessageStatutEnum;

	/** For serialization. */
	@SuppressWarnings("unused")
	private ChangerStatutMessageResult() {

	}

	public ChangerStatutMessageResult(final TypeMessageStatutEnum typeMessageStatutEnum) {
		setTypeMessageStatutEnum(typeMessageStatutEnum);
	}

	/**
	 * @param typeMessageStatutEnum
	 *          the typeMessageStatutEnum to set
	 */
	public void setTypeMessageStatutEnum(TypeMessageStatutEnum typeMessageStatutEnum) {
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

	/**
	 * @return the typeMessageStatutEnum
	 */
	public TypeMessageStatutEnum getTypeMessageStatutEnum() {
		return typeMessageStatutEnum;
	}

}