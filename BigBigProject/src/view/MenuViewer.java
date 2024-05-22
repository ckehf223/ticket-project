package view;

public class MenuViewer {
	// 처음 보이는 메뉴
	public static void startMenuView() {
		System.out.println("┌─────────────────────────────────────────┐");
		System.out.println("│ 1.로그인   2.회원가입  3.ID/PW 찾기   4.종료   │");
		System.out.println("└─────────────────────────────────────────┘");
		System.out.print("번호를 입력하세요: ");
	}

	// 로그인
	public static void loginView() {
		System.out.println("*********************");
		System.out.println("*    로   그   인     *");
		System.out.println("*********************");
	}

	// 회원가입
	public static void joinMemberView() {
		System.out.println("****************************");
		System.out.println("*      회  원  가  입        *");
		System.out.println("****************************");
	}

	
	public static void findMemberView() {
		System.out.println("*********************");
		System.out.println("*   ID / PW 찾 기    *");
		System.out.println("*********************");
	}

	// adminMenu
	public static void adminMenuView() {
		System.out.println("***************************************");
		System.out.println("* 1.회원 정보 출력    * 4.공연 정보 출력       ");
		System.out.println("* 2.회원 삭제        * 5.공연 삭제          ");
		System.out.println("* 3.공연 추가        * 6.로그아웃           ");
		System.out.println("***************************************");
		System.out.print("**번호를 입력하세요: ");
	}

	// customer mainMenu
	public static void mainMenuView() {
		System.out.println("***********************************************************");
		System.out.println("*\t\t  " + "Performance Market Menu");

		System.out.println("***********************************************************");
		System.out.println("*  1. 내 정보 확인하기  \t\t6. 예매내역 전체삭제하기");
		System.out.println("*  2. 내 정보 수정하기  \t\t7. 결제하기");
		System.out.println("*  3. 공연 정보 목록 보기\t\t8. 결제내역 보기");
		System.out.println("*  4. 공연 예매 하기    \t\t9. 고객센터");
		System.out.println("*  5. 예매내역 목록 보기 \t\t10. 로그아웃 ");
		System.out.println("***********************************************************");
		System.out.print("*메뉴 번호를 선택해주세요: ");
	}
	
	//예매내역 
	public static void printCart() {
		System.out.println("──────────────────────────── 예 매 내 역 보기 ─────────────────────────────");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("   공 연 ID    │ 공 연 명  │  공연 장소   │ 공 연 일 │  좌 석  │  수 량  │ 합 계  ");
		System.out.println("────────────────────────────────────────────────────────────────────────");

	}
	
	//결제내역
	public static void printPaymentCart() {
		System.out.println("──────────────────────────── 결 제 내 역 보기 ─────────────────────────────");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("   공 연 ID    │ 공 연 명  │  공연 장소   │ 공 연 일 │  좌 석  │  수 량  │ 합 계  ");
		System.out.println("────────────────────────────────────────────────────────────────────────");

	}
	
	//고객센터
	public static void serviceMenuView() {
		System.out.println("┌─────────────────────────────────────────────┐");
		System.out.println(" ***************** 고 객 센 터 *****************");
		System.out.println(" *       1.1대1 문의        2.내 문의내역 보기     *");
		System.out.println(" *********************************************");
		System.out.println("└─────────────────────────────────────────────┘");
		System.out.print("*번호를 선택해 주세요: ");
	}
	
	//내 문의내역
	public static void printInquiry() {
		System.out.println("──────────────────────내 문의 내역 ───────────────────────");
		System.out.println("--------------------------------------------------------");
		System.out.println(" NO │\t  질 문 \t\t│\t\t 답변 \t\t│");
		System.out.println("───────────────────────────────────────────────────────");
	}
	
}
