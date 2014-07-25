
package ro.volution.tools.transcript.tests;


import ro.volution.tools.transcript.core.EventContext;


public enum Attributes
			implements
				EventContext.AttributeIdentifier
{
	Session (),
	User (),
	X (),
	Y (),
	Z ();
	@Override
	public final String identifier () {
		return (this.name ());
	}
}
