import { createGlobalStyle } from "styled-components";
import reset from "./reset.css";
import index from "./index.css";

const GlobalStyle = createGlobalStyle`
 ${reset}
 ${index}
 @font-face {
  font-family: "GmarketSansLight";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
 @font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "GmarketSansBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@import url('https://fonts.googleapis.com/css2?family=Shrikhand&display=swap');
/* import Fonts */

html{
  font-size: 16px;
}
body{
  background-color: var(--bg-color);
  font-family: 'GmarketSansMedium', cursive;
  font-size: 1em;
}
`;
export default GlobalStyle;
