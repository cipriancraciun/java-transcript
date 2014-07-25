
package ro.volution.tools.transcript.core;


import java.util.ArrayList;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.google.common.collect.ImmutableMap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;


public class Transcript
			extends Object
{
	protected Transcript (final Context context) {
		super ();
		this.context = context;
	}
	
	public void debugging (final EventCategory category, final String message) {
		this.push (category, EventPriority.Debugging, message);
	}
	
	public void debugging (final EventCategory category, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Debugging, messageFormat, messageParts);
	}
	
	public void debugging (final EventCategory category, final Throwable exception, final ExceptionResolution resolution) {
		this.push (category, EventPriority.Debugging, exception, resolution);
	}
	
	public void debugging (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String message) {
		this.push (category, EventPriority.Debugging, exception, resolution, message);
	}
	
	public void debugging (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Debugging, exception, resolution, messageFormat, messageParts);
	}
	
	public void destroy () {
		this.context.destroy ();
	}
	
	public void error (final EventCategory category, final String message) {
		this.push (category, EventPriority.Error, message);
	}
	
	public void error (final EventCategory category, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Error, messageFormat, messageParts);
	}
	
	public void error (final EventCategory category, final Throwable exception, final ExceptionResolution resolution) {
		this.push (category, EventPriority.Error, exception, resolution);
	}
	
	public void error (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String message) {
		this.push (category, EventPriority.Error, exception, resolution, message);
	}
	
	public void error (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Error, exception, resolution, messageFormat, messageParts);
	}
	
	public Builder fork () {
		return (this.fork (false));
	}
	
	public Builder fork (final boolean dynamic) {
		return (Transcript.builder (this.context.owner, dynamic).with (this.context.attributes));
	}
	
	public Builder forkDynamic () {
		return (this.fork (true));
	}
	
	public void informative (final EventCategory category, final String message) {
		this.push (category, EventPriority.Informative, message);
	}
	
	public void informative (final EventCategory category, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Informative, messageFormat, messageParts);
	}
	
	public void informative (final EventCategory category, final Throwable exception, final ExceptionResolution resolution) {
		this.push (category, EventPriority.Informative, exception, resolution);
	}
	
	public void informative (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String message) {
		this.push (category, EventPriority.Informative, exception, resolution, message);
	}
	
	public void informative (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Informative, exception, resolution, messageFormat, messageParts);
	}
	
	public void trace (final EventCategory category, final String message) {
		this.push (category, EventPriority.Trace, message);
	}
	
	public void trace (final EventCategory category, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Trace, messageFormat, messageParts);
	}
	
	public void trace (final EventCategory category, final Throwable exception, final ExceptionResolution resolution) {
		this.push (category, EventPriority.Trace, exception, resolution);
	}
	
	public void trace (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String message) {
		this.push (category, EventPriority.Trace, exception, resolution, message);
	}
	
	public void trace (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Trace, exception, resolution, messageFormat, messageParts);
	}
	
	public void warning (final EventCategory category, final String message) {
		this.push (category, EventPriority.Warning, message);
	}
	
	public void warning (final EventCategory category, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Warning, messageFormat, messageParts);
	}
	
	public void warning (final EventCategory category, final Throwable exception, final ExceptionResolution resolution) {
		this.push (category, EventPriority.Warning, exception, resolution);
	}
	
	public void warning (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String message) {
		this.push (category, EventPriority.Warning, exception, resolution, message);
	}
	
	public void warning (final EventCategory category, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts) {
		this.push (category, EventPriority.Warning, exception, resolution, messageFormat, messageParts);
	}
	
	protected void push (final EventCategory category, final EventPriority priority, final String message) {
		this.push (category, priority, message, null, null, null);
	}
	
	protected void push (final EventCategory category, final EventPriority priority, final String messageFormat, final Object ... messageParts) {
		this.push (category, priority, messageFormat, messageParts, null, null);
	}
	
	protected void push (final EventCategory category, final EventPriority priority, final String messageFormat, final Object[] messageParts, final Throwable exception, final ExceptionResolution resolution) {
		final Level level;
		switch (priority) {
			case Error :
				level = Level.ERROR;
				break;
			case Warning :
				level = Level.WARN;
				break;
			case Informative :
				level = Level.INFO;
				break;
			case Debugging :
				level = Level.DEBUG;
				break;
			case Trace :
				level = Level.TRACE;
				break;
			default :
				throw (new AssertionError ());
		}
		final LoggingEvent event = new LoggingEvent (this.getClass ().getName (), this.context.logger, level, messageFormat, exception, messageParts);
		if (category != null)
			event.setMarker (MarkerFactory.getMarker (category.identifier ()));
		event.setMDCPropertyMap (this.context.resolveAttributes_1 ());
		this.context.logger.callAppenders (event);
	}
	
	protected void push (final EventCategory category, final EventPriority priority, final Throwable exception, final ExceptionResolution resolution) {
		this.push (category, priority, null, null, exception, resolution);
	}
	
	protected void push (final EventCategory category, final EventPriority priority, final Throwable exception, final ExceptionResolution resolution, final String message) {
		this.push (category, priority, message, null, exception, resolution);
	}
	
	protected void push (final EventCategory category, final EventPriority priority, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts) {
		this.push (category, priority, messageFormat, messageParts, exception, resolution);
	}
	
	protected final Context context;
	
	public static final Builder builder (final Object owner) {
		return (Transcript.builder (owner, false));
	}
	
	public static final Builder builder (final Object owner, final boolean dynamic) {
		return (new Builder (owner, dynamic));
	}
	
	public static final Transcript create (final Object owner) {
		return (Transcript.builder (owner).build ());
	}
	
	public static class Builder
				extends Object
	{
		protected Builder (final Object owner, final boolean dynamic) {
			super ();
			this.owner = owner;
			this.dynamic = dynamic;
			this.withs = ImmutableMap.builder ();
		}
		
		public Transcript build () {
			final Logger logger = (Logger) LoggerFactory.getLogger (this.owner.getClass ());
			final Context context = new Context (this.owner, logger, this.withs.build (), this.dynamic);
			final Transcript transcript = new Transcript (context);
			return (transcript);
		}
		
		public Builder with (final Iterable<Map.Entry<String, Object>> withs) {
			for (final Map.Entry<String, Object> entry : withs)
				this.withs.put (entry.getKey (), entry.getValue ());
			return (this);
		}
		
		public Builder with (final Map<String, Object> withs) {
			for (final Map.Entry<String, Object> entry : withs.entrySet ())
				this.withs.put (entry.getKey (), entry.getValue ());
			return (this);
		}
		
		public Builder with (final String with, final Object value) {
			this.withs.put (with, value);
			return (this);
		}
		
		protected boolean dynamic;
		protected Object owner;
		protected ImmutableMap.Builder<String, Object> withs;
	}
	
	public static class Context
				extends Object
				implements
					EventContext
	{
		protected Context (final Object owner, final Logger logger, final ImmutableMap<String, Object> attributes, final boolean dynamic) {
			super ();
			this.owner = owner;
			this.logger = logger;
			this.attributes = attributes;
			this.dynamic = dynamic;
			if (this.dynamic)
				Context.stack.get ().add (this);
		}
		
		protected void destroy () {
			if (this.dynamic) {
				final ArrayList<Context> stack = Context.stack.get ();
				final int stackSize = stack.size ();
				if (stackSize == 0)
					throw (new AssertionError ());
				final Context head = stack.remove (stackSize - 1);
				if (head != this)
					throw (new AssertionError ());
			}
		}
		
		protected ImmutableMap<String, String> resolveAttributes_1 () {
			final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder ();
			for (final Map.Entry<String, Object> entry : this.attributes.entrySet ())
				builder.put (entry.getKey (), String.valueOf (entry.getValue ()));
			for (final Context context : Context.stack.get ())
				if (context != this)
					for (final Map.Entry<String, Object> entry : context.attributes.entrySet ())
						builder.put (entry.getKey (), String.valueOf (entry.getValue ()));
			return (builder.build ());
		}
		
		protected final ImmutableMap<String, Object> attributes;
		protected final boolean dynamic;
		protected final Logger logger;
		protected final Object owner;
		private static ThreadLocal<ArrayList<Context>> stack = new ThreadLocal<ArrayList<Context>> () {
			@Override
			protected final ArrayList<Context> initialValue () {
				return (new ArrayList<Context> (128));
			}
		};
	}
}