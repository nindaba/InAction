/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.yadlings.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Stock extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -9087690741103858252L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Stock\",\"namespace\":\"com.yadlings.avro\",\"fields\":[{\"name\":\"symbol\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"industry\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"shares\",\"type\":\"int\",\"default\":0},{\"name\":\"customerId\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"sector\",\"type\":\"string\",\"default\":\"sector 1\"},{\"name\":\"sharePrice\",\"type\":\"double\",\"default\":0.0},{\"name\":\"transactionTimeStamp\",\"type\":\"string\",\"default\":\"0000-00-00\"},{\"name\":\"purchase\",\"type\":\"boolean\",\"default\":false}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Stock> ENCODER =
      new BinaryMessageEncoder<Stock>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Stock> DECODER =
      new BinaryMessageDecoder<Stock>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Stock> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Stock> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Stock> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Stock>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Stock to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Stock from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Stock instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Stock fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence symbol;
   private java.lang.CharSequence industry;
   private int shares;
   private java.lang.CharSequence customerId;
   private java.lang.CharSequence sector;
   private double sharePrice;
   private java.lang.CharSequence transactionTimeStamp;
   private boolean purchase;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Stock() {}

  /**
   * All-args constructor.
   * @param symbol The new value for symbol
   * @param industry The new value for industry
   * @param shares The new value for shares
   * @param customerId The new value for customerId
   * @param sector The new value for sector
   * @param sharePrice The new value for sharePrice
   * @param transactionTimeStamp The new value for transactionTimeStamp
   * @param purchase The new value for purchase
   */
  public Stock(java.lang.CharSequence symbol, java.lang.CharSequence industry, java.lang.Integer shares, java.lang.CharSequence customerId, java.lang.CharSequence sector, java.lang.Double sharePrice, java.lang.CharSequence transactionTimeStamp, java.lang.Boolean purchase) {
    this.symbol = symbol;
    this.industry = industry;
    this.shares = shares;
    this.customerId = customerId;
    this.sector = sector;
    this.sharePrice = sharePrice;
    this.transactionTimeStamp = transactionTimeStamp;
    this.purchase = purchase;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return symbol;
    case 1: return industry;
    case 2: return shares;
    case 3: return customerId;
    case 4: return sector;
    case 5: return sharePrice;
    case 6: return transactionTimeStamp;
    case 7: return purchase;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: symbol = (java.lang.CharSequence)value$; break;
    case 1: industry = (java.lang.CharSequence)value$; break;
    case 2: shares = (java.lang.Integer)value$; break;
    case 3: customerId = (java.lang.CharSequence)value$; break;
    case 4: sector = (java.lang.CharSequence)value$; break;
    case 5: sharePrice = (java.lang.Double)value$; break;
    case 6: transactionTimeStamp = (java.lang.CharSequence)value$; break;
    case 7: purchase = (java.lang.Boolean)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'symbol' field.
   * @return The value of the 'symbol' field.
   */
  public java.lang.CharSequence getSymbol() {
    return symbol;
  }


  /**
   * Sets the value of the 'symbol' field.
   * @param value the value to set.
   */
  public void setSymbol(java.lang.CharSequence value) {
    this.symbol = value;
  }

  /**
   * Gets the value of the 'industry' field.
   * @return The value of the 'industry' field.
   */
  public java.lang.CharSequence getIndustry() {
    return industry;
  }


  /**
   * Sets the value of the 'industry' field.
   * @param value the value to set.
   */
  public void setIndustry(java.lang.CharSequence value) {
    this.industry = value;
  }

  /**
   * Gets the value of the 'shares' field.
   * @return The value of the 'shares' field.
   */
  public int getShares() {
    return shares;
  }


  /**
   * Sets the value of the 'shares' field.
   * @param value the value to set.
   */
  public void setShares(int value) {
    this.shares = value;
  }

  /**
   * Gets the value of the 'customerId' field.
   * @return The value of the 'customerId' field.
   */
  public java.lang.CharSequence getCustomerId() {
    return customerId;
  }


  /**
   * Sets the value of the 'customerId' field.
   * @param value the value to set.
   */
  public void setCustomerId(java.lang.CharSequence value) {
    this.customerId = value;
  }

  /**
   * Gets the value of the 'sector' field.
   * @return The value of the 'sector' field.
   */
  public java.lang.CharSequence getSector() {
    return sector;
  }


  /**
   * Sets the value of the 'sector' field.
   * @param value the value to set.
   */
  public void setSector(java.lang.CharSequence value) {
    this.sector = value;
  }

  /**
   * Gets the value of the 'sharePrice' field.
   * @return The value of the 'sharePrice' field.
   */
  public double getSharePrice() {
    return sharePrice;
  }


  /**
   * Sets the value of the 'sharePrice' field.
   * @param value the value to set.
   */
  public void setSharePrice(double value) {
    this.sharePrice = value;
  }

  /**
   * Gets the value of the 'transactionTimeStamp' field.
   * @return The value of the 'transactionTimeStamp' field.
   */
  public java.lang.CharSequence getTransactionTimeStamp() {
    return transactionTimeStamp;
  }


  /**
   * Sets the value of the 'transactionTimeStamp' field.
   * @param value the value to set.
   */
  public void setTransactionTimeStamp(java.lang.CharSequence value) {
    this.transactionTimeStamp = value;
  }

  /**
   * Gets the value of the 'purchase' field.
   * @return The value of the 'purchase' field.
   */
  public boolean getPurchase() {
    return purchase;
  }


  /**
   * Sets the value of the 'purchase' field.
   * @param value the value to set.
   */
  public void setPurchase(boolean value) {
    this.purchase = value;
  }

  /**
   * Creates a new Stock RecordBuilder.
   * @return A new Stock RecordBuilder
   */
  public static com.yadlings.avro.Stock.Builder newBuilder() {
    return new com.yadlings.avro.Stock.Builder();
  }

  /**
   * Creates a new Stock RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Stock RecordBuilder
   */
  public static com.yadlings.avro.Stock.Builder newBuilder(com.yadlings.avro.Stock.Builder other) {
    if (other == null) {
      return new com.yadlings.avro.Stock.Builder();
    } else {
      return new com.yadlings.avro.Stock.Builder(other);
    }
  }

  /**
   * Creates a new Stock RecordBuilder by copying an existing Stock instance.
   * @param other The existing instance to copy.
   * @return A new Stock RecordBuilder
   */
  public static com.yadlings.avro.Stock.Builder newBuilder(com.yadlings.avro.Stock other) {
    if (other == null) {
      return new com.yadlings.avro.Stock.Builder();
    } else {
      return new com.yadlings.avro.Stock.Builder(other);
    }
  }

  /**
   * RecordBuilder for Stock instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Stock>
    implements org.apache.avro.data.RecordBuilder<Stock> {

    private java.lang.CharSequence symbol;
    private java.lang.CharSequence industry;
    private int shares;
    private java.lang.CharSequence customerId;
    private java.lang.CharSequence sector;
    private double sharePrice;
    private java.lang.CharSequence transactionTimeStamp;
    private boolean purchase;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.yadlings.avro.Stock.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.symbol)) {
        this.symbol = data().deepCopy(fields()[0].schema(), other.symbol);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.industry)) {
        this.industry = data().deepCopy(fields()[1].schema(), other.industry);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.shares)) {
        this.shares = data().deepCopy(fields()[2].schema(), other.shares);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.customerId)) {
        this.customerId = data().deepCopy(fields()[3].schema(), other.customerId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.sector)) {
        this.sector = data().deepCopy(fields()[4].schema(), other.sector);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.sharePrice)) {
        this.sharePrice = data().deepCopy(fields()[5].schema(), other.sharePrice);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.transactionTimeStamp)) {
        this.transactionTimeStamp = data().deepCopy(fields()[6].schema(), other.transactionTimeStamp);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.purchase)) {
        this.purchase = data().deepCopy(fields()[7].schema(), other.purchase);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing Stock instance
     * @param other The existing instance to copy.
     */
    private Builder(com.yadlings.avro.Stock other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.symbol)) {
        this.symbol = data().deepCopy(fields()[0].schema(), other.symbol);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.industry)) {
        this.industry = data().deepCopy(fields()[1].schema(), other.industry);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.shares)) {
        this.shares = data().deepCopy(fields()[2].schema(), other.shares);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.customerId)) {
        this.customerId = data().deepCopy(fields()[3].schema(), other.customerId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.sector)) {
        this.sector = data().deepCopy(fields()[4].schema(), other.sector);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.sharePrice)) {
        this.sharePrice = data().deepCopy(fields()[5].schema(), other.sharePrice);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.transactionTimeStamp)) {
        this.transactionTimeStamp = data().deepCopy(fields()[6].schema(), other.transactionTimeStamp);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.purchase)) {
        this.purchase = data().deepCopy(fields()[7].schema(), other.purchase);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'symbol' field.
      * @return The value.
      */
    public java.lang.CharSequence getSymbol() {
      return symbol;
    }


    /**
      * Sets the value of the 'symbol' field.
      * @param value The value of 'symbol'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setSymbol(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.symbol = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'symbol' field has been set.
      * @return True if the 'symbol' field has been set, false otherwise.
      */
    public boolean hasSymbol() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'symbol' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearSymbol() {
      symbol = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'industry' field.
      * @return The value.
      */
    public java.lang.CharSequence getIndustry() {
      return industry;
    }


    /**
      * Sets the value of the 'industry' field.
      * @param value The value of 'industry'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setIndustry(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.industry = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'industry' field has been set.
      * @return True if the 'industry' field has been set, false otherwise.
      */
    public boolean hasIndustry() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'industry' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearIndustry() {
      industry = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'shares' field.
      * @return The value.
      */
    public int getShares() {
      return shares;
    }


    /**
      * Sets the value of the 'shares' field.
      * @param value The value of 'shares'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setShares(int value) {
      validate(fields()[2], value);
      this.shares = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'shares' field has been set.
      * @return True if the 'shares' field has been set, false otherwise.
      */
    public boolean hasShares() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'shares' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearShares() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'customerId' field.
      * @return The value.
      */
    public java.lang.CharSequence getCustomerId() {
      return customerId;
    }


    /**
      * Sets the value of the 'customerId' field.
      * @param value The value of 'customerId'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setCustomerId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.customerId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'customerId' field has been set.
      * @return True if the 'customerId' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'customerId' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearCustomerId() {
      customerId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'sector' field.
      * @return The value.
      */
    public java.lang.CharSequence getSector() {
      return sector;
    }


    /**
      * Sets the value of the 'sector' field.
      * @param value The value of 'sector'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setSector(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.sector = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'sector' field has been set.
      * @return True if the 'sector' field has been set, false otherwise.
      */
    public boolean hasSector() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'sector' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearSector() {
      sector = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'sharePrice' field.
      * @return The value.
      */
    public double getSharePrice() {
      return sharePrice;
    }


    /**
      * Sets the value of the 'sharePrice' field.
      * @param value The value of 'sharePrice'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setSharePrice(double value) {
      validate(fields()[5], value);
      this.sharePrice = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'sharePrice' field has been set.
      * @return True if the 'sharePrice' field has been set, false otherwise.
      */
    public boolean hasSharePrice() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'sharePrice' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearSharePrice() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'transactionTimeStamp' field.
      * @return The value.
      */
    public java.lang.CharSequence getTransactionTimeStamp() {
      return transactionTimeStamp;
    }


    /**
      * Sets the value of the 'transactionTimeStamp' field.
      * @param value The value of 'transactionTimeStamp'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setTransactionTimeStamp(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.transactionTimeStamp = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'transactionTimeStamp' field has been set.
      * @return True if the 'transactionTimeStamp' field has been set, false otherwise.
      */
    public boolean hasTransactionTimeStamp() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'transactionTimeStamp' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearTransactionTimeStamp() {
      transactionTimeStamp = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'purchase' field.
      * @return The value.
      */
    public boolean getPurchase() {
      return purchase;
    }


    /**
      * Sets the value of the 'purchase' field.
      * @param value The value of 'purchase'.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder setPurchase(boolean value) {
      validate(fields()[7], value);
      this.purchase = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'purchase' field has been set.
      * @return True if the 'purchase' field has been set, false otherwise.
      */
    public boolean hasPurchase() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'purchase' field.
      * @return This builder.
      */
    public com.yadlings.avro.Stock.Builder clearPurchase() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stock build() {
      try {
        Stock record = new Stock();
        record.symbol = fieldSetFlags()[0] ? this.symbol : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.industry = fieldSetFlags()[1] ? this.industry : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.shares = fieldSetFlags()[2] ? this.shares : (java.lang.Integer) defaultValue(fields()[2]);
        record.customerId = fieldSetFlags()[3] ? this.customerId : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.sector = fieldSetFlags()[4] ? this.sector : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.sharePrice = fieldSetFlags()[5] ? this.sharePrice : (java.lang.Double) defaultValue(fields()[5]);
        record.transactionTimeStamp = fieldSetFlags()[6] ? this.transactionTimeStamp : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.purchase = fieldSetFlags()[7] ? this.purchase : (java.lang.Boolean) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Stock>
    WRITER$ = (org.apache.avro.io.DatumWriter<Stock>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Stock>
    READER$ = (org.apache.avro.io.DatumReader<Stock>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.symbol);

    out.writeString(this.industry);

    out.writeInt(this.shares);

    out.writeString(this.customerId);

    out.writeString(this.sector);

    out.writeDouble(this.sharePrice);

    out.writeString(this.transactionTimeStamp);

    out.writeBoolean(this.purchase);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.symbol = in.readString(this.symbol instanceof Utf8 ? (Utf8)this.symbol : null);

      this.industry = in.readString(this.industry instanceof Utf8 ? (Utf8)this.industry : null);

      this.shares = in.readInt();

      this.customerId = in.readString(this.customerId instanceof Utf8 ? (Utf8)this.customerId : null);

      this.sector = in.readString(this.sector instanceof Utf8 ? (Utf8)this.sector : null);

      this.sharePrice = in.readDouble();

      this.transactionTimeStamp = in.readString(this.transactionTimeStamp instanceof Utf8 ? (Utf8)this.transactionTimeStamp : null);

      this.purchase = in.readBoolean();

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.symbol = in.readString(this.symbol instanceof Utf8 ? (Utf8)this.symbol : null);
          break;

        case 1:
          this.industry = in.readString(this.industry instanceof Utf8 ? (Utf8)this.industry : null);
          break;

        case 2:
          this.shares = in.readInt();
          break;

        case 3:
          this.customerId = in.readString(this.customerId instanceof Utf8 ? (Utf8)this.customerId : null);
          break;

        case 4:
          this.sector = in.readString(this.sector instanceof Utf8 ? (Utf8)this.sector : null);
          break;

        case 5:
          this.sharePrice = in.readDouble();
          break;

        case 6:
          this.transactionTimeStamp = in.readString(this.transactionTimeStamp instanceof Utf8 ? (Utf8)this.transactionTimeStamp : null);
          break;

        case 7:
          this.purchase = in.readBoolean();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










