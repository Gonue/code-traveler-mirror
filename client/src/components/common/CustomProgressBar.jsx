import React, { useState, useEffect } from "react";
import styled from "styled-components";

//이미지 불러오기
import train from "../../assets/images/train.png";
import sign from "../../assets/images/sign.png";

// 프로그레스바입니다.
// progress 상태에 따라 증가도를 나타냅니다. 상태는 1~100의 정수입니다.

/**
 *
 * @param progress 진행도를 입력하세요.
 * @param marginBottom 마진Bottiom 값을 입력하세요. px단위
 * @param feat simple 값을 props로 준다면 상태바의 기차 이미지가 사라집니다.
 * @returns 상태바가 현재 진행도를 나타냅니다.
 */
function CustomProgressBar({ progress, marginBottom, feat }) {
  return (
    <ProgressBarContainer marginBottom={marginBottom}>
      {feat !== "simple" && <Sign></Sign>}
      <Bar progress={progress} feat={feat}></Bar>
      {feat !== "simple" && <Train progress={progress}></Train>}
    </ProgressBarContainer>
  );
}

const ProgressBarContainer = styled.div`
  position: relative;
  width: 100%;
  background-color: ${props => props.theme.gray50};
  border-radius: 5px;
  border: 1px solid ${props => props.theme.black};
  box-sizing: border-box;
  margin-bottom: ${props => props.marginBottom || "50px"};
`;

const Bar = styled.div`
  height: ${({ feat }) => (feat === "simple" && "1.25em") || "24px"};
  border-radius: 5px;
  background-color: ${props => props.theme.main};
  width: ${({ progress }) => progress}%;
`;

const Train = styled.div`
  bottom: 22px;
  transform: translateX(-50%);
  position: absolute;
  left: ${({ progress }) => progress}%;
  width: 55px;
  height: 50px;
  background-image: url(${train}); // 이미지를 background-image로 설정합니다.
  background-size: cover; // 이미지가 컨테이너 크기에 맞게 조절되도록 합니다.
  background-position: center; // 이미지를 가운데 정렬합니다.
`;

const Sign = styled.div`
  transform: translateX(-15%);
  position: absolute;
  background-size: cover; // 이미지가 컨테이너 크기에 맞게 조절되도록 합니다.
  background-position: center; // 이미지를 가운데 정렬합니다.
  width: 55px; // 원하는 너비를 설정하세요.
  height: 50px; // 원하는 높이를 설정하세요.
  bottom: 25px;
  background-image: url(${sign});
`;
export default CustomProgressBar;
