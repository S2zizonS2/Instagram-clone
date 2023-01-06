
import React from "react";
import NaviList from "./NaviList";
import BaseProps from "@/Type/BaseProps";
import ThemeContext from "@/store/ThemeContext";
import styled from "@emotion/styled";

function Aside(props: BaseProps) {
    const { themeStyle } = React.useContext(ThemeContext);
    const { backgroundColor, color } = themeStyle.aside;
    return (
        <AsideWrap
            className={props?.className}
            backgroundColor={backgroundColor}
            color={color}
        >
            <NaviList />
        </AsideWrap>
    );
}

const AsideWrap = styled.aside<{
    backgroundColor: string;
    color: string;
}>`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    padding: 1.5rem;  
    border-right: 1px solid var(--grey-200);
    background-color: ${(props) => props.backgroundColor};
    color: ${(props) => props.color};

    // 태블릿
    @media screen and (max-width: 1239px) and (min-width: 769px){
        padding: 1rem;
    }

    // 모바일
    @media screen and (max-width: 768px) {
        border: none;
        padding: 0;
    }
`;

export default Aside;