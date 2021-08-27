package xyz.auriium.openmineplatform.api.telescope;

public interface TelescopeMapping<T,I> {

   T calculate(I input);

}
