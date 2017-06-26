# 网络链接检验

* [使用步骤](#使用步骤)
    * [引用](#引用)
    * [调用](#调用)
* [其他](#其他)
    
    
## 使用步骤

#### 引用

```
dependencies {
    compile 'com.github.pwittchen:reactivenetwork-rx2:0.9.1'
}
```

#### 调用

```
ReactiveNetwork.observeNetworkConnectivity(this)
    .subscribeOn(Schedulers.io())
   // anything else what you can do with RxJava
   .observeOn(AndroidSchedulers.mainThread())
   .subscribe(new Consumer<Connectivity>() {
       @Override public void accept(final Connectivity connectivity) {
           // do something with connectivity
           // you can call connectivity.getState();
           // connectivity.getType(); or connectivity.toString();
           NetworkInfo.State state = connectivity.getState();
           String name = connectivity.getTypeName();
           tvContent.setText(String.format("state: %s, typeName: %s", state, name));
       }
   });
```
可以根据状态和名称进行其他操作  
另外，Connectivity 中有一些方法可以调用

```
Connectivity create()
Connectivity create(Context context)

NetworkInfo.State getState()
NetworkInfo.DetailedState getDetailedState()
int getType()
int getSubType()
boolean isAvailable()
boolean isFailover()
boolean isRoaming()
String getTypeName()
String getSubTypeName()
String getReason()
String getExtraInfo()
```


## 其他

reactive network包中可以查看Internet是否连接， 
通过observeInternetConnectivity方法，  
但是暂时未用到，所以没有去验证。