**The steps to develop your own extension of the temporal converter in order to support new date-time type:**

* Create a new class in the package where the converters registration class (such as the included _ru.rencredit
.autotests.commons.util.convert.Converters_ class) has been located. 

for example: _SqlDateConverter_ within the _ru.rencredit.autotests.commons.util.convert.datetime.converters_ package.


* Set the class visibility to the ```public``` level.
````java
public class SqlDateConverter {
}
````

* Mark the class with the annotation _@ConverterFor_
```java
@ConverterFor(java.sql.Date.class)
public class SqlDateConverter {
}
````

* Make the class implemented the _ru.rencredit.autotests.commons.util.convert.datetime.TemporalConverter_ interface: 
```java
@ConverterFor(java.sql.Date.class)
public class SqlDateConverter implements TemporalConverter<java.sql.Date> {
}
````

* Implement the methods of conversions from/to _java.time.ZonedDateTime_;
```java
@ConverterFor(java.sql.Date.class)
public class SqlDateConverter implements TemporalConverter<java.sql.Date> {

    /** {@inheritDoc} */
    @Override
    public Optional<java.sql.Date> nominated(final ZonedDateTime value) {
        return Optional.of(value)
                .map(ZonedDateTime::toLocalDate)
                .map(java.sql.Date::valueOf);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final java.sql.Date value) {
        return Optional.ofNullable(value)
                .map(java.sql.Date::toLocalDate)
                .map(localDate -> localDate.atStartOfDay(ZoneOffset.UTC));
    }
}
````

* Create/Update the appropriate tests.

That's all

