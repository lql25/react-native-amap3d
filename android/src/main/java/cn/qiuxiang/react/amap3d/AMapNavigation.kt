package cn.qiuxiang.react.amap3d

import android.annotation.SuppressLint
import com.amap.api.navi.AMapNavi
import com.amap.api.navi.AMapNaviListener
import com.amap.api.navi.AMapNaviView
import com.amap.api.navi.AMapNaviViewListener
import com.amap.api.navi.enums.NaviType
import com.amap.api.navi.enums.PathPlanningStrategy
import com.amap.api.navi.model.*
import com.autonavi.tbt.TrafficFacilityInfo
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter

@SuppressLint("ViewConstructor")
class AMapNavigation(context: ThemedReactContext) :
        AMapNaviView(context.currentActivity), AMapNaviViewListener, AMapNaviListener {
    val eventEmitter: RCTEventEmitter = context.getJSModule(RCTEventEmitter::class.java)
    val navigation = AMapNavi.getInstance(context)!!

    init {
        super.onCreate(null)
        this.setAMapNaviViewListener(this)
        navigation.addAMapNaviListener(this)
    }

    fun calculateWalkRoute(args: ReadableArray?) {
        navigation.calculateWalkRoute(
                latLngFromReadableMap(args?.getMap(0)!!),
                latLngFromReadableMap(args.getMap(1)))
    }

    fun calculateRideRoute(args: ReadableArray?) {
        navigation.calculateRideRoute(
                latLngFromReadableMap(args?.getMap(0)!!),
                latLngFromReadableMap(args.getMap(1)))
    }

    fun calculateDriveRoute(args: ReadableArray?) {
        navigation.calculateDriveRoute(
                listOf(latLngFromReadableMap(args?.getMap(0)!!)),
                listOf(latLngFromReadableMap(args.getMap(1))),
                listOf(),
                PathPlanningStrategy.DRIVING_DEFAULT
        )
    }

    fun start() {
        navigation.startNavi(NaviType.GPS)
    }

    fun latLngFromReadableMap(map: ReadableMap): NaviLatLng {
        return NaviLatLng(map.getDouble("latitude"), map.getDouble("longitude"))
    }

    fun sendEvent(name: String, data: WritableMap = Arguments.createMap()) {
        eventEmitter.receiveEvent(id, name, data)
    }

    override fun onCalculateRouteSuccess(array: IntArray) {
        sendEvent("onCalculateRouteSuccess")
    }

    override fun onCalculateRouteFailure(errorCode: Int) {
        val event = Arguments.createMap()
        event.putInt("errorCode", errorCode)
        sendEvent("onCalculateRouteFailure", event)
    }

    override fun onInitNaviSuccess() {
        sendEvent("onReady")
    }

    override fun onServiceAreaUpdate(p0: Array<out AMapServiceAreaInfo>?) {
    }

    override fun onEndEmulatorNavi() {
    }

    override fun onArrivedWayPoint(p0: Int) {
    }

    override fun onArriveDestination() {
    }

    override fun onPlayRing(p0: Int) {
    }

    override fun onTrafficStatusUpdate() {
    }

    override fun onGpsOpenStatus(p0: Boolean) {
    }

    override fun updateAimlessModeCongestionInfo(p0: AimLessModeCongestionInfo?) {
    }

    override fun showCross(p0: AMapNaviCross?) {
    }

    override fun onGetNavigationText(p0: Int, p1: String?) {
    }

    override fun onGetNavigationText(p0: String?) {
    }

    override fun updateAimlessModeStatistics(p0: AimLessModeStat?) {
    }

    override fun hideCross() {
    }

    override fun onInitNaviFailure() {
    }

    override fun onReCalculateRouteForTrafficJam() {
    }

    override fun hideLaneInfo() {
    }

    override fun onNaviInfoUpdated(p0: AMapNaviInfo?) {
    }

    override fun updateCameraInfo(p0: Array<out AMapNaviCameraInfo>?) {
    }

    override fun onLocationChange(p0: AMapNaviLocation?) {
    }

    override fun onReCalculateRouteForYaw() {
    }

    override fun onStartNavi(p0: Int) {
    }

    override fun notifyParallelRoad(p0: Int) {
    }

    override fun OnUpdateTrafficFacility(p0: AMapNaviTrafficFacilityInfo?) {
    }

    override fun OnUpdateTrafficFacility(p0: Array<out AMapNaviTrafficFacilityInfo>?) {
    }

    override fun OnUpdateTrafficFacility(p0: TrafficFacilityInfo?) {
    }

    override fun showLaneInfo(p0: Array<out AMapLaneInfo>?, p1: ByteArray?, p2: ByteArray?) {
    }

    override fun onNaviInfoUpdate(p0: NaviInfo?) {
    }

    override fun onNaviTurnClick() {
    }

    override fun onNaviViewLoaded() {
    }

    override fun onNaviBackClick(): Boolean {
        return false
    }

    override fun onNaviMapMode(p0: Int) {
    }

    override fun onNextRoadClick() {
    }

    override fun onScanViewButtonClick() {
    }

    override fun onLockMap(p0: Boolean) {
    }

    override fun onNaviSetting() {
    }

    override fun onNaviCancel() {
    }
}
