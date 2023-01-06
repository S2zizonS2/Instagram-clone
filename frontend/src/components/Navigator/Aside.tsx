import { css } from "@emotion/react";
import React from "react";
import NaviList from "./NaviList";
import BaseProps from "@/Type/BaseProps";

function Aside(props: BaseProps) {
    return (
        <aside
            css={style}
            className={props?.className}
        >
            <NaviList />
        </aside>
    );
}

const style = css`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    padding: 1.5rem;  
    border-right: 1px solid var(--grey-200);


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