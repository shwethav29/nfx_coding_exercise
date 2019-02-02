package com.coding.exercise.content.playlist;
/**
 * In case no legal PlayList could be formend the {@link LegalPlayListGeneratorImpl} throws this exception
 * @author svasanth
 *
 */
public class NoLegalPlayListException extends RuntimeException{
	
	private static final long serialVersionUID = -2675457817469009437L;

	public NoLegalPlayListException() {
		super();
	}

	public NoLegalPlayListException(String message) {
		super(message);
	}

}
