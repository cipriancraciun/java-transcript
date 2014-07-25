
package ro.volution.tools.transcript.tests;


import org.junit.Test;

import ro.volution.tools.transcript.core.EventCategory;
import ro.volution.tools.transcript.core.ExceptionResolution;
import ro.volution.tools.transcript.core.Transcript;


public class Example
{
	@Test
	public void exampleA () {
		{
			this.exampleZ ("without dynamic context A, before;");
		}
		{
			final Transcript transcript = this.transcript ().with ("x", 5).with ("y", 6).build ();
			try {
				transcript.informative (Categories.A, "operation on object `{}`;", 10);
				this.exampleZ ("with dynamic context A, inside;");
			} finally {
				transcript.destroy ();
			}
		}
		{
			this.exampleZ ("without dynamic context A, after;");
		}
	}
	
	@Test
	public void exampleB () {
		final Transcript transcript = this.transcript ().with ("z", 7).build ();
		try {
			this.exampleZ ("with dynamic context B, inside;");
		} finally {
			transcript.destroy ();
		}
	}
	
	@Test
	public void exampleC () {
		final Transcript transcript = this.transcript ().build ();
		try {
			transcript.informative (Categories.A, new Throwable (), ExceptionResolution.Handled);
			transcript.warning (Categories.A, new Throwable (new Throwable ()), ExceptionResolution.Ignored);
		} finally {
			transcript.destroy ();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void exampleD () {
		final Transcript transcript = this.transcript ().build ();
		try {
			this.exampleA ();
			this.exampleB ();
			this.exampleC ();
		} finally {
			transcript.destroy ();
		}
	}
	
	protected void exampleZ (final String message) {
		final Transcript transcript = Transcript.builder (this).with ("something", 0).build ();
		transcript.informative (Categories.A, message);
	}
	
	protected Transcript.Builder transcript () {
		// NOTE: In "controller" super class constructor:
		final Transcript this_transcript = Transcript.create (this);
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
