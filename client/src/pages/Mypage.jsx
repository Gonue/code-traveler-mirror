import styled from "styled-components";
import { useSelector } from "react-redux";
import PageContainer from "../components/common/PageContainer.jsx";
import Profile from "../components/Mypage/Profile.jsx";
import Level from "../components/Mypage/Level.jsx";
import BtnTabContainer from "../components/Mypage/BtnTabContainer.jsx";
import { useEffect, useState } from "react";
import { getData } from "../api/apiUtil.js";

export default function Mypage() {
  const { userInfo } = useSelector(state => state.user);

  return (
    <PageContainer>
      <MyContainer className="Mycontaner">
        <Profile userInfo={userInfo} />
        <Level userInfo={userInfo} />
      </MyContainer>
      <BtnTabContainer />
    </PageContainer>
  );
}

const MyContainer = styled.main`
  width: 100%;
  display: flex;
  margin-bottom: 110px;
  box-sizing: border-box;
  @media ${props => props.theme.mediaQuery.mobile} {
    flex-direction: column;
    margin-bottom: 50px;
  }
`;