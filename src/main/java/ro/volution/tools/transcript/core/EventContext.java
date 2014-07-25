
package ro.volution.tools.transcript.core;


public interface EventContext
{
	public abstract Iterable<Attribute> attributes ();
	
	public static interface AttributeIdentifier {
		public abstract String identifier ();
	}
	
	public static final class Attribute
			extends Object
	{
		private Attribute (final AttributeIdentifier identifier, final Object value) {
			super ();
			this.identifier = identifier;
			this.value = value;
		}
		
		public final AttributeIdentifier identifier;
		public final Object value;
	}
}
