package org.usfirst.frc.team1592.robot.subsystems;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

/**
 *  Robot Telemetry Class
 */
public class RobotTelemetry {

	/** Output Stream */
	private final OutputStream out;

	/** Registered Data Containers */
	private final List<DataStream> streams; 

	/** Initialized/Locked State Indicator */
	private boolean locked;
	/****************/
	/** Constructor */
	/****************/

	/** Standard Constructor */
	public RobotTelemetry(OutputStream output, DoubleSupplier time) {
		if (output==null) {throw new NullPointerException();}
		out = output;
		streams = new ArrayList<>();
		locked = false;
		registerDoubleStream("System Time", "s", time);
	}	

	public RobotTelemetry(FileOutputStream output, DoubleSupplier time) {
		if (output==null) {throw new NullPointerException();}
		out = output;
		streams = new ArrayList<>();
		locked = false;
		registerDoubleStream("System Time", "s", time);
	}	
		// TODO Auto-generated constructor stub
	

	/************************/
	/** Static Constructors */
	/************************/
	/** Current Time In Milliseconds Constructor */
	public static RobotTelemetry currentTimeMillis(OutputStream output) {
		return new RobotTelemetry(output, () -> {return System.currentTimeMillis();});
	}

	/** Zeroed Current Time In Milliseconds Constructor */
	public static RobotTelemetry zeroedCurrentTimeMillis(OutputStream output) {
		final long initialTime = System.currentTimeMillis();
		return new RobotTelemetry(output, () -> {return System.currentTimeMillis()-initialTime;});
	}



	/**********************************************/
	/** Data Registration Methods (Unlocked Only) */
	/**********************************************/

	/**
	 * Registers a double based data source for storage into the telemetry stream. The
	 * data source can provide both a source name and units.
	 *
	 * @param name  the source name, null implies empty string
	 * @param units  the source units, null implies empty string
	 * @param source  the data supplier
	 * @throws IllegalStateException if the object is locked (i.e. {@link #isLocked()} is true)
	 */
	public void registerDoubleStream(String name, String units, DoubleSupplier source) throws IllegalStateException {
		if (isLocked()) {throw new IllegalStateException();}
		streams.add(new DoubleDataStream(name, units, source));
	}

	/**
	 * Registers a long based data source for storage into the telemetry stream. The
	 * data source can provide both a source name and units.
	 *
	 * @param name  the source name, null implies empty string
	 * @param units  the source units, null implies empty string
	 * @param source  the data supplier
	 * @throws IllegalStateException if the object is locked (i.e. {@link #isLocked()} is true)
	 */
	public void registerLongStream(String name, String units, LongSupplier source) throws IllegalStateException {
		if (isLocked()) {throw new IllegalStateException();}
		streams.add(new LongDataStream(name, units, source));
	}

	/**
	 * Registers a integer based data source for storage into the telemetry stream. The
	 * data source can provide both a source name and units.
	 *
	 * @param name  the source name, null implies empty string
	 * @param units  the source units, null implies empty string
	 * @param source  the data supplier
	 * @throws IllegalStateException if the object is locked (i.e. {@link #isLocked()} is true)
	 */
	public void registerIntStream(String name, String units, IntSupplier source) throws IllegalStateException {
		if (isLocked()) {throw new IllegalStateException();}
		streams.add(new IntDataStream(name, units, source));
	}

	/**
	 * Registers a boolean based data source for storage into the telemetry stream. The
	 * data source can provide both a source name and units.
	 *
	 * @param name  the source name, null implies empty string
	 * @param units  the source units, null implies empty string
	 * @param source  the data supplier
	 * @throws IllegalStateException if the object is locked (i.e. {@link #isLocked()} is true)
	 */
	public void registerBooleanStream(String name, String units, BooleanSupplier source) throws IllegalStateException {
		if (isLocked()) {throw new IllegalStateException();}
		streams.add(new BooleanDataStream(name, units, source));
	}



	/************************************/
	/** End Registration/Locked Methods */
	/************************************/

	/**
	 * Returns whether the class is locked (i.e. post registration phase) or not.
	 *
	 * @return whether the class is locked or not
	 */
	public final boolean isLocked() {
		return locked;
	}

	/**
	 * This method notifies the class that the registration period is over and the
	 * class should be locked from future registrations. All future invocations of
	 * {@link #isLocked()} will return {@code true}. This method does nothing if the
	 * object is already locked (i.e. {@link #isLocked()} is already true).
	 */
	public final void endRegistration() {
		locked = true;
	}



	/**************************************/
	/** Data Output Methods (Locked Only) */
	/**************************************/

	/**
	 * Outputs the stream header information, in consistent order. This
	 * should be edited to format, however desired.
	 *
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalStateException if the object is not locked (i.e. {@link #isLocked()} is false)
	 */
	public final void outputHeader() throws IOException {
		if (!isLocked()) {throw new IllegalStateException();}
		for (DataStream s : streams) {
			// Use the output stream input in the constructor
			// Use DataStream.name() and DataStream.units();
		}
	}

	/**
	 * Outputs the stream data, in consistent order. This
	 * should be edited to format, however desired.
	 *
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalStateException if the object is not locked (i.e. {@link #isLocked()} is false)
	 */
	public final void outputData() throws IOException {
		if (!isLocked()) {throw new IllegalStateException();}
		for (DataStream s : streams) {
			// Use the output stream input in the constructor
			// Use DataStream.valueAsString(), DataStream.valueAsByteArray(), or DataStream.valueAsNumber()
			// depending on how you want to output the data
		}
	}



	/*******************************/
	/** Abstract Class: DataStream */
	/*******************************/

	/** Internal Data Stream Container */
	private static abstract class DataStream {

		/** Information */
		private final String _name;
		private final String _units;

		/** Standard Constructor */
		private DataStream(String name, String units) {
			_name = name==null ? "" : name;
			_units = units==null ? "" : units;
		}

		/** Returns the data stream name */
		public String name() {return _name;}

		/** Returns the data stream units */
		public String units() {return _units;}

		/** Returns the number of bytes for a data stream value */
		public abstract int bytes();

		/** Returns the current data stream value as a string */
		public abstract String valuesAsString();

		/** Returns the current data stream value as a byte array */
		public abstract byte[] valueAsByteArray();

		/** Returns the current data stream value as a number */
		public abstract Number valueAsNumber();

	}



	/*************************************/
	/** Internal Class: DoubleDataStream */
	/*************************************/

	/** Internal Double Data Stream Container */
	private static final class DoubleDataStream extends DataStream {

		/** Supplier */
		private final DoubleSupplier _source;

		/** Standard Constructor */
		DoubleDataStream(String name, String units, DoubleSupplier source) {
			super(name, units);
			if (source==null) {throw new NullPointerException();}
			_source = source;
		}

		@Override public int bytes() {return Double.BYTES;}

		/** Internal Value Accessor */
		private final double getValue() {return _source.getAsDouble();}

		@Override public String valuesAsString() {return Double.toString(getValue());}

		@Override public byte[] valueAsByteArray() {
			byte[] bytes = new byte[bytes()];
			ByteBuffer.wrap(bytes).putDouble(getValue());
			return bytes;
		}

		@Override public Number valueAsNumber() {return Double.valueOf(getValue());}

	}



	/***********************************/
	/** Internal Class: LongDataStream */
	/***********************************/

	/** Internal Long Data Stream Container */
	private static final class LongDataStream extends DataStream {

		/** Supplier */
		private final LongSupplier _source;

		/** Standard Constructor */
		LongDataStream(String name, String units, LongSupplier source) {
			super(name, units);
			if (source==null) {throw new NullPointerException();}
			_source = source;
		}

		@Override public int bytes() {return Long.BYTES;}

		/** Internal Value Accessor */
		private final long getValue() {return _source.getAsLong();}

		@Override public String valuesAsString() {return Long.toString(getValue());}

		@Override public byte[] valueAsByteArray() {
			byte[] bytes = new byte[bytes()];
			ByteBuffer.wrap(bytes).putLong(getValue());
			return bytes;
		}

		@Override public Number valueAsNumber() {return Long.valueOf(getValue());}

	}



	/**********************************/
	/** Internal Class: IntDataStream */
	/**********************************/

	/** Internal Integer Data Stream Container */
	private static final class IntDataStream extends DataStream {

		/** Supplier */
		private final IntSupplier _source;

		/** Standard Constructor */
		IntDataStream(String name, String units, IntSupplier source) {
			super(name, units);
			if (source==null) {throw new NullPointerException();}
			_source = source;
		}

		@Override public int bytes() {return Integer.BYTES;}

		/** Internal Value Accessor */
		private final int getValue() {return _source.getAsInt();}

		@Override public String valuesAsString() {return Integer.toString(getValue());}

		@Override public byte[] valueAsByteArray() {
			byte[] bytes = new byte[bytes()];
			ByteBuffer.wrap(bytes).putInt(getValue());
			return bytes;
		}

		@Override public Number valueAsNumber() {return Integer.valueOf(getValue());}

	}



	/**************************************/
	/** Internal Class: BooleanDataStream */
	/**************************************/

	/** Internal Integer Data Stream Container */
	private static final class BooleanDataStream extends DataStream {

		/** Supplier */
		private final BooleanSupplier _source;

		/** Standard Constructor */
		BooleanDataStream(String name, String units, BooleanSupplier source) {
			super(name, units);
			if (source==null) {throw new NullPointerException();}
			_source = source;
		}

		@Override public int bytes() {return 1;}

		/** Internal Value Accessor */
		private final boolean getValue() {return _source.getAsBoolean();}

		@Override public String valuesAsString() {return Boolean.toString(getValue());}

		@Override public byte[] valueAsByteArray() {
			byte[] bytes = new byte[bytes()];
			ByteBuffer.wrap(bytes).put((byte) (getValue() ? 1 : 0));
			return bytes;
		}

		@Override public Number valueAsNumber() {return Integer.valueOf(getValue() ? 1 : 0);}

	}

}
