import { css } from "@emotion/react";
import React from "react";
import BaseProps from "@/Type/BaseProps";

/**
 * 구분선 또는 컴포넌트
 * @returns 
 */
function TextOR(props: BaseProps) {
    return (
        <div
            css={style}
            className={props?.className}
        >
            <hr />
            <p>{`또는`}</p>
            <hr />
        </div>
    );
}

const style = css`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    hr {
        width: 100%;
        border: 0.5px solid var(--grey-200);
    }

    p {
        display: inline-flex;
        flex-wrap: nowrap;
        font-size: 0.9rem;
        white-space: nowrap;
        padding: 0 1.5rem;
        color: var(--grey-300);
        font-weight: 800;
    }

`;

export default React.memo(TextOR);