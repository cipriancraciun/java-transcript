import org.junit.Test;

import ro.volution.tools.transcript.core.EventCategory;
import ro.volution.tools.transcript.core.Transcript;


public class Example
{
	@Test
	public void exampleA () {
		{
			this.exampleC1 ("without dynamic context A, before;");
		}
		{
			final Transcript transcript = this.transcript ().with ("x", 5).with ("y", 6).build ();
			try {
				transcript.informative (Categories.A, "operation on object `{}`;", 10);
				this.exampleC1 ("with dynamic context A, inside;");
				this.exampleB ();
			} finally {
				transcript.destroy ();
			}
		}
		{
			this.exampleC1 ("without dynamic context A, after;");
		}
	}
	
	@Test
	public void exampleB () {
		final Transcript transcript = this.transcript ().with ("z", 7).build ();
		try {
			this.exampleC1 ("with dynamic context B, inside;");
		} finally {
			transcript.destroy ();
		}
	}
	
	protected void exampleC1 (final String message) {
		final Transcript transcript = Transcript.builder (new Object ()).with ("something", 0).build ();
		transcript.informative (Categories.A, message);
	}
	
	protected Transcript.Builder transcript () {
		final Object this_ = new Object ();
		// NOTE: In "controller" super class constructor:
		final Transcript this_transcript = Transcript.create (this_);
		// NOTE: In "controller" super class helper method:
		return this_transcript.forkDynamic ().with ("session", 1).with ("user", 2);
	}
	
	public enum Categories
				implements
					EventCategory
	{
		A;
		@Override
		public String identifier () {
			return (this.name ());
		}
	}
}
