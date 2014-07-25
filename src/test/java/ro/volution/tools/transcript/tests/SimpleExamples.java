
package ro.volution.tools.transcript.tests;


import org.junit.Test;

import ro.volution.tools.transcript.core.Transcript;


public class SimpleExamples
{
	public SimpleExamples () {
		this.transcript = Transcript.create (this);
	}
	
	@Test
	public void exampleA () {
		this.transcript.informative (Categories.A, "some generic event...");
	}
	
	@Test
	public void exampleB () {
		final Transcript transcript = this.transcript.fork ().with (Attributes.X, 1).with (Attributes.Y, 2).build ();
		transcript.informative (Categories.A, "some contextual event (1)...");
		transcript.informative (Categories.A, "some contextual event (2)...");
	}
	
	protected final Transcript transcript;
}
