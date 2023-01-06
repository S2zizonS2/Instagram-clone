import { css } from "@emotion/react";
import React from "react";

interface NaviItemProps {
    icon: any;
    text: string;
}


function NaviItem(props: NaviItemProps) {
    return (
        <li css={style}>
            {props.icon}
            <p>{props.text}</p>
        </li>
    );
}

const style = css`
    display: flex;
    align-items: center;
    cursor: pointer;
    font-weight: 400;
    font-size: 1.8rem;
    p {
        margin-left: 1rem;
        font-size: 1rem;
    }
    // 태블릿
    @media screen and (max-width: 1239px) and (min-width: 769px){
        justify-content: center;
        align-items: center;
        p {
            display: none;
        }
    }

    // 데스크탑, 테블릿
    @media screen and (min-width: 769px) {
        min-height: 48px;
        margin-bottom: 0.5rem;
    }

    // 모바일
    @media screen and (max-width: 768px) {
        justify-content: center;
        align-items: center;

        p {
            display: none;
        }
    }
`;

export default NaviItem;