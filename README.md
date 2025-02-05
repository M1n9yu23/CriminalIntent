Fragment: 액티비티의 작업 수행을 대행할 수 있는 컨트롤러 객체, 프래그먼트 사이에 인자 전달 (프래그먼트 간의 이동)

RecyclerView: 동적인 스크롤 목록 생성 및 항목 상호작용 처리. (Adapter, Holder(RecyclerView의 ItemView, 즉 리사이클러뷰의 단위))

ConstraintLayout: 중첩 레이아웃은 안드로이드 운영체제가 처리하고 보여주는 데 시간이 걸림. 즉 성능이 저하됨. ConstraintLayout은 중첩하지 않고도 복잡한 레이아웃 만들 수 있음.

Room 데이터베이스: 로컬 데이터 저장 및 관리
- 엔티티(Entity), DAO, Repository 패턴 활용.
- DAO를 이용한 데이터 쿼리 처리.
- 백그라운드 스레드에서 데이터베이스 작업 처리.

LiveData: 앱의 서로 다른 부분 간에 데이터 전달을 쉽게 만들기 위해서 사용, 옵저버 패턴으로 작업들이 이루어짐, 이것을 활용하여 데이터 변화에 따른 UI 업데이트 처리.

즉, 예로 들면 이 앱의 CrimeRepository에서 데이터를 전달하는 경우다. LiveData는 스레드 간에도 데이터를 전달할 수 있어서 백그라운드 스레드에서 main 스레드로 데이터를 전달할 수 있다.

DialogFragment: 사용자와의 상호작용을 위한 다이얼로그 표시. (캘린더)

앱 바 및 메뉴: 앱 바에 메뉴 추가 및 관리. (+를 클릭하면 CrimeListFragment -> CrimeFragment 이동)

인텐트 : 장치의 다른 앱에 있는 액티비티를 시작시킬 수 있음. --> 명시적 인텐트(시작시킬 액티비티 클래스를 지정하면 안드로이드 운영체제가 해당 액티비티를 시작시킴.), 암시적 인텐트(해야 할 작업을 알려주면 안드로이드 운영체제가 이 작업을 수행하는 데 적합한 앱의 액티비티를 찾아서 시작시킴)

여기서는 암시적 인텐트만 사용
- 카메라를 이용한 사진 촬영
- 연락처 앱 및 텍스트 전송 앱

리소스 지역화: 구성 수식자를 만들어 놓으면 안드로이드 시스템이 해당 안드로이드 장치에 맞게 자동 선택함.

접근성 향상: TalkBack을 사용하여 앱의 접근성을 강화할 수 있음. 이런 접근성은 대등한 사용자 경험을 만들어야 한다.

<h2 id="0">🖥️ Result</h2>

<p float="left">  
<img width="40%" src="https://github.com/user-attachments/assets/18168164-82c8-47fe-9766-b11c451776c0">

<img width="40%" src="https://github.com/user-attachments/assets/8ca2e7c8-4df1-44cf-9090-e9bd416d29a0">
</p>