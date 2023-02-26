"use client";

import { useEffect, useState } from "react";

export interface BookmarkListItemProps {
  title: string;
  url: string;
  createdAt: string;
  updatedAt?: string;
}

function formatDate(date: string) {
  return new Date(Date.parse(date)).toLocaleString("en-us", {
    year: "numeric",
    month: "short",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
  });
}

const BookmarkListItem = ({
  title,
  url,
  createdAt,
  updatedAt,
}: BookmarkListItemProps) => {
  const [displayCreatedDate, setDisplayDate] = useState(createdAt);
  useEffect(() => setDisplayDate(formatDate(displayCreatedDate)));
  return (
    <a
      href={`https://${url}`}
      target="_blank"
      className="list-group-item list-group-item-action flex-column align-items-start"
    >
      <div className="d-flex w-100 justify-content-between">
        <h5 className="mb-1">{title}</h5>
        <small>{displayCreatedDate}</small>
        {/* TODO: make it like edited 3 days ago or created 3 days ago */}
      </div>
      <p className="mb-1">{url}</p>
      {/* <small>Donec id elit non mi porta.</small> */}
    </a>
  );
};

export default BookmarkListItem;
