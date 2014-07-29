
package ro.volution.tools.transcript.core;


public interface EventContext
{
	public abstract Iterable<Attribute> attributes ();
	
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
		
		public static final Attribute create (final AttributeIdentifier identifier, final Object value) {
			return (new Attribute (identifier, value));
		}
		
		public static final Attribute create (final String identifier, final Object value) {
			return (Attribute.create (StringAttributeIdentifier.resolve (identifier), value));
		}
	}
	
	public static interface AttributeIdentifier
	{
		public abstract String identifier ();
	}
	
	public static final class StringAttributeIdentifier
				extends Object
				implements
					AttributeIdentifier
	{
		private StringAttributeIdentifier (final String token) {
			super ();
			this.token = token;
		}
		
		@Override
		public final String identifier () {
			return (this.token);
		}
		
		public final String token;
		
		public static final StringAttributeIdentifier resolve (final String token) {
			return (new StringAttributeIdentifier (token));
		}
	}
}
