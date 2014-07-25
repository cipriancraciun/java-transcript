
package ro.volution.tools.transcript.core;


public interface Backend
{
	public abstract void push (final EventContext context, final EventCategory category, final EventPriority priority, final String message);
	
	public abstract void push (final EventContext context, final EventCategory category, final EventPriority priority, final String messageFormat, final Object ... messageParts);
	
	public abstract void push (final EventContext context, final EventCategory category, final EventPriority priority, final Throwable exception, final ExceptionResolution resolution);
	
	public abstract void push (final EventContext context, final EventCategory category, final EventPriority priority, final Throwable exception, final ExceptionResolution resolution, final String message);
	
	public abstract void push (final EventContext context, final EventCategory category, final EventPriority priority, final Throwable exception, final ExceptionResolution resolution, final String messageFormat, final Object ... messageParts);
}
