
package ro.volution.tools.transcript.core;


import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ro.volution.tools.transcript.core.EventContext.Attribute;
import ro.volution.tools.transcript.core.EventContext.AttributeIdentifier;


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
		return (Transcript.builder (this.context.owner, this.context, dynamic));
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
		event.setMDCPropertyMap (this.context.getAttributesMdc ());
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
	
	public static final Builder builder (final Class<?> owner) {
		return (Transcript.builder (owner, false));
	}
	
	public static final Builder builder (final Class<?> owner, final boolean dynamic) {
		return (Transcript.builder (owner, dynamic));
	}
	
	public static final Builder builder (final Object owner) {
		return (Transcript.builder (owner, false));
	}
	
	public static final Builder builder (final Object owner, final boolean dynamic) {
		return (Transcript.builder (owner, null, dynamic));
	}
	
	public static final Builder builderDynamic (final Class<?> owner) {
		return (Transcript.builder (owner, true));
	}
	
	public static final Builder builderDynamic (final Object owner) {
		return (Transcript.builder (owner, true));
	}
	
	public static final Transcript create (final Class<?> owner) {
		return (Transcript.builder (owner).build ());
	}
	
	public static final Transcript create (final Object owner) {
		return (Transcript.builder (owner).build ());
	}
	
	protected static final Builder builder (final Object owner, final Context parent, final boolean dynamic) {
		return (new Builder (owner, parent, dynamic));
	}
	
	public static class Builder
				extends Object
	{
		protected Builder (final Object owner, final Context parent, final boolean dynamic) {
			super ();
			this.owner = owner;
			this.parent = parent;
			this.dynamic = dynamic;
			this.withs = ImmutableList.builder ();
		}
		
		public Transcript build () {
			final Class<?> ownerClass = (this.owner instanceof Class) ? (Class<?>) this.owner : this.owner.getClass ();
			final Logger logger = (Logger) LoggerFactory.getLogger (ownerClass);
			final Context context = new Context (this.owner, logger, this.withs.build (), this.parent, this.dynamic);
			final Transcript transcript = new Transcript (context);
			return (transcript);
		}
		
		public Builder with (final Attribute ... attributes) {
			this.withs.add (attributes);
			return (this);
		}
		
		public Builder with (final Attribute attribute) {
			this.withs.add (attribute);
			return (this);
		}
		
		public Builder with (final AttributeIdentifier identifier, final Object value) {
			return (this.with (Attribute.create (identifier, value)));
		}
		
		public Builder with (final Iterable<Attribute> attributes) {
			this.withs.addAll (attributes);
			return (this);
		}
		
		public Builder with (final Map<String, Object> withs) {
			for (final Map.Entry<String, Object> entry : withs.entrySet ())
				this.with (entry.getKey (), entry.getValue ());
			return (this);
		}
		
		public Builder with (final String identifier, final Object value) {
			return (this.with (Attribute.create (identifier, value)));
		}
		
		protected final boolean dynamic;
		protected final Object owner;
		protected final Context parent;
		protected final ImmutableList.Builder<Attribute> withs;
	}
	
	public static class Context
				extends Object
				implements
					EventContext
	{
		protected Context (final Object owner, final Logger logger, final ImmutableList<Attribute> attributes, final Context parent, final boolean dynamic) {
			super ();
			this.owner = owner;
			this.logger = logger;
			this.attributes = attributes;
			this.parent = parent;
			this.dynamic = dynamic;
			this.dynamicParent = Context.dynamicHead.get ();
			this.cachedCounter = 0;
			this.cachedAttributes = null;
			this.cachedAttributesMdc = null;
			if (this.dynamic)
				Context.pushDynamicContext (this);
		}
		
		@Override
		public Iterable<EventContext.Attribute> attributes () {
			this.validateCached ();
			if (this.cachedAttributes == null)
				this.cachedAttributes = this.resolveAttributes ();
			return (this.cachedAttributes);
		}
		
		public ImmutableMap<String, String> getAttributesMdc () {
			this.validateCached ();
			if (this.cachedAttributesMdc == null)
				this.cachedAttributesMdc = this.resolveAttributesMdc ();
			return (this.cachedAttributesMdc);
		}
		
		protected void destroy () {
			if (this.dynamic)
				Context.popDynamicContext (this);
		}
		
		protected ImmutableList<Attribute> resolveAttributes () {
			final ImmutableList.Builder<Attribute> builder = ImmutableList.builder ();
			final IdentityHashMap<AttributeIdentifier, Attribute> identifiers = new IdentityHashMap<AttributeIdentifier, Attribute> ();
			Context.resolveAttributes_include (this, true, true, builder, identifiers);
			final Context dynamicHead = Context.dynamicHead.get ();
			if ((dynamicHead != null) && (dynamicHead != this) && (dynamicHead != this.parent) && (dynamicHead != this.dynamicParent))
				Context.resolveAttributes_include (dynamicHead, true, true, builder, identifiers);
			return (builder.build ());
		}
		
		protected ImmutableMap<String, String> resolveAttributesMdc () {
			final HashMap<String, String> attributes = new HashMap<String, String> ();
			for (final Attribute attribute : this.attributes ()) {
				final String identifier = attribute.identifier.identifier ();
				if (attributes.containsKey (identifier))
					continue;
				final String value = String.valueOf (attribute.value);
				attributes.put (identifier, value);
			}
			return (ImmutableMap.copyOf (attributes));
		}
		
		protected void validateCached () {
			final int currentCounter = Context.getDynamicCounter ();
			if (currentCounter == this.cachedCounter)
				return;
			this.cachedCounter = currentCounter;
			this.cachedAttributes = null;
			this.cachedAttributesMdc = null;
		}
		
		protected final ImmutableList<Attribute> attributes;
		protected final boolean dynamic;
		protected final Context dynamicParent;
		protected final Logger logger;
		protected final Object owner;
		protected final Context parent;
		private ImmutableList<Attribute> cachedAttributes;
		private ImmutableMap<String, String> cachedAttributesMdc;
		private int cachedCounter;
		
		protected static final void advanceDynamicCounter () {
			Context.dynamicCounter.set (Integer.valueOf (Context.getDynamicCounter () + 1));
		}
		
		protected static final int getDynamicCounter () {
			final Integer counter = Context.dynamicCounter.get ();
			return ((counter != null) ? counter.intValue () : 0);
		}
		
		protected static final void popDynamicContext (final Context head) {
			if (head != Context.dynamicHead.get ())
				throw (new AssertionError ());
			// FIXME: Replace head with previous head (not with current head dynamic parent)!
			Context.dynamicHead.set (head.dynamicParent);
			Context.advanceDynamicCounter ();
		}
		
		protected static final void pushDynamicContext (final Context head) {
			Context.dynamicHead.set (head);
			Context.advanceDynamicCounter ();
		}
		
		protected static final void resolveAttributes_include (final Attribute attribute, final ImmutableList.Builder<Attribute> builder, final IdentityHashMap<AttributeIdentifier, Attribute> identifiers) {
			if (identifiers.containsKey (attribute.identifier))
				return;
			builder.add (attribute);
			identifiers.put (attribute.identifier, attribute);
		}
		
		protected static final void resolveAttributes_include (final Context context, final boolean includeParent, final boolean includeDynamicParent, final ImmutableList.Builder<Attribute> builder, final IdentityHashMap<AttributeIdentifier, Attribute> identifiers) {
			Context.resolveAttributes_include (context.attributes, builder, identifiers);
			if (includeParent)
				for (Context parent = context.parent; parent != null; parent = parent.parent)
					Context.resolveAttributes_include (parent, includeParent, includeDynamicParent, builder, identifiers);
			if (includeDynamicParent)
				for (Context parent = context.dynamicParent; parent != null; parent = parent.dynamicParent)
					Context.resolveAttributes_include (parent, includeParent, includeDynamicParent, builder, identifiers);
		}
		
		protected static final void resolveAttributes_include (final Iterable<Attribute> attributes, final ImmutableList.Builder<Attribute> builder, final IdentityHashMap<AttributeIdentifier, Attribute> identifiers) {
			for (final Attribute attribute : attributes)
				Context.resolveAttributes_include (attribute, builder, identifiers);
		}
		
		static {
			dynamicCounter = new ThreadLocal<Integer> ();
			dynamicHead = new ThreadLocal<Context> ();
		}
		private static final ThreadLocal<Integer> dynamicCounter;
		private static final ThreadLocal<Context> dynamicHead;
	}
}
