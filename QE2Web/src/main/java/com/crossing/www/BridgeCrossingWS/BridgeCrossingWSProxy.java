package com.crossing.www.BridgeCrossingWS;

public class BridgeCrossingWSProxy implements com.crossing.www.BridgeCrossingWS.BridgeCrossingWS_PortType {
  private String _endpoint = null;
  private com.crossing.www.BridgeCrossingWS.BridgeCrossingWS_PortType bridgeCrossingWS_PortType = null;
  
  public BridgeCrossingWSProxy() {
    _initBridgeCrossingWSProxy();
  }
  
  public BridgeCrossingWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initBridgeCrossingWSProxy();
  }
  
  private void _initBridgeCrossingWSProxy() {
    try {
      bridgeCrossingWS_PortType = (new com.crossing.www.BridgeCrossingWS.BridgeCrossingWS_ServiceLocator()).getBridgeCrossingPort();
      if (bridgeCrossingWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bridgeCrossingWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bridgeCrossingWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bridgeCrossingWS_PortType != null)
      ((javax.xml.rpc.Stub)bridgeCrossingWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.crossing.www.BridgeCrossingWS.BridgeCrossingWS_PortType getBridgeCrossingWS_PortType() {
    if (bridgeCrossingWS_PortType == null)
      _initBridgeCrossingWSProxy();
    return bridgeCrossingWS_PortType;
  }
  
  public java.lang.String sendCrossingDetails(java.lang.String vrn, java.lang.String gateno, java.lang.String direction) throws java.rmi.RemoteException{
    if (bridgeCrossingWS_PortType == null)
      _initBridgeCrossingWSProxy();
    return bridgeCrossingWS_PortType.sendCrossingDetails(vrn, gateno, direction);
  }
  
  
}