
package ro.volution.tools.transcript.tests;


import org.junit.Test;

import ro.volution.tools.transcript.core.EventCategory;
import ro.volution.tools.transcript.core.ExceptionResolution;
import ro.volution.tools.transcript.core.Transcript;


public class DynamicExamples
{
	@Test
	public void exampleA () {
		{
			this.exampleZ (Categories.A, "without dynamic context A, before;");
		}
		{
			final Transcript transcript = this.transcriptForSession ().with (Attributes.X, 5).with (Attributes.Y, 6).build ();
			try {
				transcript.informative (Categories.A, "operation on object `{}`;", 10);
				this.exampleZ (Categories.A, "with dynamic context A, inside;");
			} finally {
				transcript.destroy ();
			}
		}
		{
			this.exampleZ (Categories.A, "without dynamic context A, after;");
		}
	}
	
	@Test
	public void exampleB () {
		final Transcript transcript = this.transcriptForSession ().with (Attributes.Z, 7).build ();
		try {
			this.exampleZ (Categories.B, "with dynamic context B, inside;");
		} finally {
			transcript.destroy ();
		}
	}
	
	@Test
	public void exampleC () {
		final Transcript transcript = this.transcriptForSession ().build ();
		try {
			transcript.informative (Categories.C, new Throwable (), ExceptionResolution.Handled);
			transcript.warning (Categories.C, new Throwable (new Throwable ()), ExceptionResolution.Ignored);
		} finally {
			transcript.destroy ();
		}
	}
	
	@Test
	public void exampleD () {
		final Transcript transcript = Transcript.builder (this).with (Attributes.X, 5).build ();
		transcript.informative (Categories.D, "without dynamic context;");
		final Transcript dynamicTranscript = this.transcriptForSession ().build ();
		try {
			transcript.informative (Categories.D, "with dynamic context;");
		} finally {
			dynamicTranscript.destroy ();
		}
		transcript.informative (Categories.D, "without dynamic context;");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void exampleE () {
		final Transcript transcript = this.transcriptForSession ().build ();
		try {
			this.exampleB ();
		} finally {
			transcript.destroy ();
		}
	}
	
	protected void exampleZ (final EventCategory category, final String message) {
		final Transcript transcript = Transcript.builder (this).with ("something", 0).build ();
		transcript.informative (category, message);
	}
	
	protected Transcript.Builder transcriptForSession () {
		return Transcript.builderDynamic (this).with (Attributes.Session, 1).with (Attributes.User, 2);
	}
}
