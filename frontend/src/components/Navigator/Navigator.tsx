import { css } from "@emotion/react";
import React from "react";
import NaviList from "./NaviList";
import BaseProps from "@/Type/BaseProps";

function Navigator(props: BaseProps) {
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
`;

export default Navigator;