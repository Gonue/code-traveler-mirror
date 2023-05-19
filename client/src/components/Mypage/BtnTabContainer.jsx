import styled from "styled-components";
import BtnDropdown from "./BtnDropdown.jsx";
import BtnTab from "./BtnTab.jsx";
import { useEffect, useState } from "react";
import Dashbord from "./Dashbord.jsx";
import MyDebate from "./MyComment.jsx";
import Setting from "./Setting.jsx";

export default function BtnTabContainer() {
  // 창 크기에 따라 버튼 탭(PC)/드롭다운(모바일) 변경하기
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });

  const handleResize = () => {
    setWindowSize({
      width: window.innerWidth,
      height: window.innerHeight,
    });
  };
  useEffect(() => {
    window.addEventListener("resize", handleResize);
    return () => {
      // 이벤트리스너 지워주기
      window.removeEventListener("resize", handleResize);
    };
  }, [window.innerWidth]);

  // 하위 컴포넌트의 name 을 저장해서 여기에서 tab/dropdown 으로 내려주기
  const [selected, setSelected] = useState(0);
  const menuArr = [
    { name: "대시보드", content: <Dashbord /> },
    { name: "내가 쓴 토론 글", content: <MyDebate /> },
    { name: "설정", content: <Setting /> },
  ];
  console.log(`컨테이너 selected : ${selected}`);
  return (
    <>
      <BtnBox>
        {windowSize.width < 767 ? (
          <BtnDropdown
            menuArr={menuArr}
            selected={selected}
            setSelected={setSelected}
          />
        ) : (
          <BtnTab
            menuArr={menuArr}
            selected={selected}
            setSelected={setSelected}
          />
        )}
      </BtnBox>
    </>
  );
}

const BtnBox = styled.div`
  & > div {
    margin-bottom: 40px;
  }
`;