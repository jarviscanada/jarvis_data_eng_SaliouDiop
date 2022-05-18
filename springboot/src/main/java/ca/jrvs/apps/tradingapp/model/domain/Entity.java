package ca.jrvs.apps.tradingapp.model.domain;

public interface Entity<ID>{
  ID getId();
  void setId(ID id);
}
