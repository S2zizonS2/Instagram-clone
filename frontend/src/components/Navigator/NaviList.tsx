import React from "react";
import { MdHomeFilled } from "react-icons/md";
import { BsSearch } from "react-icons/bs";
import { FiPlusSquare } from "react-icons/fi";

import NaviItem from "./NaviItem";
import { css } from "@emotion/react";

function NaviList() {
    const naviItems: any = [
        { icon: <MdHomeFilled />, text: "홈" },
        { icon: <FiPlusSquare />, text: "추가" },
        { icon: <BsSearch />, text: "검색" },
    ];
    return (
        <ul css={style}>
            {naviItems.map((item: any, key: number) => (
                <NaviItem
                    key={key}
                    {...item}
                />
            ))}
        </ul>
    );
}

const style = css`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    // 모바일
    @media screen and (max-width: 768px){
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
        border-top: 1px solid var(--grey-200);
    }
`;

export default NaviList;